package zuja.buzz.gpstracking.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.R;
import zuja.buzz.gpstracking.activities.MainActivity;
import zuja.buzz.gpstracking.models.DriverModel;
import zuja.buzz.gpstracking.utils.ValidationUtils;
import zuja.buzz.gpstracking.utils.ViewUtils;
import zuja.buzz.gpstracking.viewmodels.DriverProfileViewModel;
import zuja.buzz.gpstracking.viewmodels.factories.DriverProfileViewModelFactory;

import static android.app.Activity.RESULT_OK;

/**
 * Fragment for showing driver data. Fields become editable with the floating action button.
 */
public class DriverProfileFragment extends Fragment {

    /**
     * fragment TAG
     */
    public static final String TAG = "DRIVER_PROFILE_FRAGMENT_TAG";
    /**
     * Request code for "select picture" intent.
     */
    private static final int PICTURE_REQUEST_CODE = 1;

    private DriverProfileViewModel driverProfileViewModel;
    /**
     * Flag used for editing the driver profile data
     */
    private boolean isEditMode;

    @Inject
    DriverProfileViewModelFactory factory;

    @InjectView(R.id.profile_picture)
    ImageView profilePicture;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.lastName)
    EditText lastName;
    @InjectView(R.id.age)
    EditText age;
    @InjectView(R.id.plate_number)
    EditText plateNumber;
    @InjectView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @InjectView(R.id.name_til)
    TextInputLayout nameTil;
    @InjectView(R.id.lastName_til)
    TextInputLayout lastNameTil;
    @InjectView(R.id.age_til)
    TextInputLayout ageTil;
    @InjectView(R.id.plate_number_til)
    TextInputLayout plateNumberTil;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GPSTrackingApplication) getActivity().getApplication()).getFactoryComponent().inject(this);
        driverProfileViewModel = ViewModelProviders.of(this, factory).get(DriverProfileViewModel.class);
        driverProfileViewModel.init(((MainActivity) getActivity()).getDriver());
        driverProfileViewModel.getDriverLiveData().observe(this, new Observer<DriverModel>() {
            @Override
            public void onChanged(@Nullable DriverModel driverModel) {
                if (driverModel != null) updateViews(driverModel);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_profile, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * Used to update the data in views.
     * @param driverModel
     */
    private void updateViews(DriverModel driverModel) {
        name.setText(driverModel.getName() == null ? "" : driverModel.getName());
        lastName.setText(driverModel.getLastName() == null ? "" : driverModel.getLastName());
        String ageValue = driverModel.getAge() == 0 ? "" : String.valueOf(driverModel.getAge());
        age.setText(ageValue);
        plateNumber.setText(driverModel.getPlateNumber() == null ? "" : driverModel.getPlateNumber());
        Bitmap bitmap = tryTocreateBitmapFromUri(driverModel.getProfilePicture());
        if(bitmap != null) {
            profilePicture.setImageBitmap(bitmap);
        }
    }

    /**
     * Tries to get a bitmap from the provided uri.
     * @param uri
     * @return
     */
    private Bitmap tryTocreateBitmapFromUri(String uri){
        Bitmap bitmap = null;
        if(!TextUtils.isEmpty(uri)) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(uri));
            } catch (IOException e) {
                Log.d("CREATE_BITMAP", "Failed to create bitmap from uri!");
            }
        }
        return bitmap;
    }

    /**
     * Used to update the viewModel data. Used after saving changes from editMode.
     */
    private void setDriverData(){
        driverProfileViewModel.getDriverLiveData().getValue().setName(name.getText().toString());
        driverProfileViewModel.getDriverLiveData().getValue().setLastName(lastName.getText().toString());
        driverProfileViewModel.getDriverLiveData().getValue().setAge(Integer.valueOf(age.getText().toString()));
        driverProfileViewModel.getDriverLiveData().getValue().setPlateNumber(plateNumber.getText().toString());
    }

    /**
     * Floating button click listener. Toggles between editable states and saves data.
     */
    @OnClick(R.id.floating_action_button)
    public void onFloatingButtonClicked() {
        if (isEditMode) {
            if (validateInput()) {
                setDriverData();
                driverProfileViewModel.saveDriver();
                isEditMode = false;
                floatingActionButton.setImageResource(R.drawable.ic_edit_black_24dp);
            }
        } else {
            isEditMode = true;
            floatingActionButton.setImageResource(R.drawable.ic_tick_black_24dp);
        }
        ViewUtils.toggleEditableView(isEditMode, name, lastName, age, plateNumber);
    }

    /**
     * Checks the validity of user input before updating the database.
     * @return
     */
    private boolean validateInput() {
        boolean formOK;
        if (!(ValidationUtils.isTextWithdashes(name.getText().toString())
                || ValidationUtils.isTextWithdashes(name.getText().toString()))) {
            nameTil.setError(getResources().getString(R.string.error_validation_allowed_textWithDashes));
            formOK = false;
        } else {
            formOK = true;
            nameTil.setError(null);
        }

        if (!(ValidationUtils.isTextWithdashes(lastName.getText().toString())
                || ValidationUtils.isTextWithdashes(lastName.getText().toString()))) {
            lastNameTil.setError(getResources().getString(R.string.error_validation_allowed_textWithDashes));
            formOK = false;
        } else {
            formOK = true;
            lastNameTil.setError(null);
        }

        if (!ValidationUtils.isNumbersOnly(age.getText().toString())) {
            ageTil.setError(getResources().getString(R.string.error_validation_allowed_numbersOnly));
            formOK = false;
        } else {
            formOK = true;
            ageTil.setError(null);
        }

        if (!ValidationUtils.isAlphaNumericWithdashes(plateNumber.getText().toString())) {
            plateNumberTil.setError(getResources().getString(R.string.error_validation_allowed_alphaNumericWithDashes));
            formOK = false;
        } else {
            formOK = true;
            plateNumberTil.setError(null);
        }
        return formOK;
    }

    /**
     * Profile picture click listener. Opens the default select a picture app from the user's device.
     */
    @OnClick(R.id.profile_picture)
    public void onProfilePictureClicked() {
        if(isEditMode) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), PICTURE_REQUEST_CODE);
        }
    }

    /**
     * This is called after the user picks a picture.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == PICTURE_REQUEST_CODE){
                Uri selectedImageUri = data.getData();
                int takeFlags = data.getFlags();
                takeFlags &= Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
                getActivity().getContentResolver().takePersistableUriPermission(selectedImageUri, takeFlags);
                profilePicture.setImageURI(selectedImageUri);
                driverProfileViewModel.getDriverLiveData().getValue().setProfilePicture(selectedImageUri.toString());
            }
        }
    }
}
