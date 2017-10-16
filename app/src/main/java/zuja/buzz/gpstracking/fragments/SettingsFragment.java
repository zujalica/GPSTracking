package zuja.buzz.gpstracking.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import zuja.buzz.gpstracking.Constants;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.R;
import zuja.buzz.gpstracking.activities.MainActivity;
import zuja.buzz.gpstracking.viewmodels.SettingsViewModel;
import zuja.buzz.gpstracking.viewmodels.factories.SettingsViewModelFactory;

public class SettingsFragment extends Fragment {

    public static final String TAG = "SETTINGS_FRAGMENT_TAG";

    private SettingsViewModel settingsViewModel;
    private GPSTrackingApplication application;

    @Inject
    SettingsViewModelFactory factory;

    @InjectView(R.id.cro_tick_imageView)
    ImageView croTickImageView;
    @InjectView(R.id.croatian_row)
    ConstraintLayout croatianRow;
    @InjectView(R.id.eng_tick_imageview)
    ImageView engTickImageview;
    @InjectView(R.id.english_row)
    ConstraintLayout englishRow;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.inject(this, view);
        application = ((GPSTrackingApplication) getActivity().getApplication());
        application.getFactoryComponent().inject(this);
        settingsViewModel = ViewModelProviders.of(this, factory).get(SettingsViewModel.class);
        settingsViewModel.init(((MainActivity) getActivity()).getSettings());
        setupViews();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.croatian_row, R.id.english_row})
    public void onRowClicked(View view) {
        switch (view.getId()) {
            case R.id.croatian_row:
                if(!TextUtils.equals(settingsViewModel.getSettings().getLocaleLanguage(), Constants.LOCALE_LANGUAGE_CROATIAN)){
                    settingsViewModel.getSettings().setLocaleStrings(Constants.LOCALE_LANGUAGE_CROATIAN, Constants.LOCALE_COUNTRY_CROATIA);
                    croTickImageView.setVisibility(View.VISIBLE);
                    engTickImageview.setVisibility(View.GONE);
                    settingsViewModel.updateSettings();
                    ((MainActivity)getActivity()).changeLocale(settingsViewModel.getSettings().getLocale());
                    refreshFragment();
                }
                break;
            case R.id.english_row:
                if(!TextUtils.equals(settingsViewModel.getSettings().getLocaleLanguage(), Constants.LOCALE_LANGUAGE_ENGLISH)){
                    settingsViewModel.getSettings().setLocaleStrings(Constants.LOCALE_LANGUAGE_ENGLISH, Constants.LOCALE_COUNTRY_USA);
                    croTickImageView.setVisibility(View.GONE);
                    engTickImageview.setVisibility(View.VISIBLE);
                    settingsViewModel.updateSettings();
                    ((MainActivity)getActivity()).changeLocale(settingsViewModel.getSettings().getLocale());
                    refreshFragment();
                }
                break;
        }
    }

    private void setupViews(){
        switch (settingsViewModel.getSettings().getLocaleLanguage()){
            case Constants.LOCALE_LANGUAGE_CROATIAN:
                croTickImageView.setVisibility(View.VISIBLE);
                break;
            case Constants.LOCALE_LANGUAGE_ENGLISH:
                engTickImageview.setVisibility(View.VISIBLE);
                break;
            default:
                engTickImageview.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void refreshFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
