package com.chesapeaketechnology.salute;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.chesapeaketechnology.salute.model.SaluteReport;

public class SecondFragmentActivity extends Fragment
{
    private static final String LOG_TAG = SecondFragmentActivity.class.getSimpleName();

    private SaluteReport saluteReport;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_second_activity, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        extractSaluteReport();

        view.findViewById(R.id.button_next).setOnClickListener(view1 -> updateSaluteReportAndPassOn());
    }

    /**
     * Pull the Salute Report from the fragment's arguments and set it as an instance variable.
     */
    private void extractSaluteReport()
    {
        final Bundle arguments = getArguments();
        if (arguments == null)
        {
            Log.wtf(LOG_TAG, "The arguments bundle was null when creating the Activity Fragment (Second Fragment)");
        } else
        {
            saluteReport = SecondFragmentActivityArgs.fromBundle(arguments).getSaluteReport();
        }
    }

    /**
     * Update the Salute Report with the information the user provided in this fragment's UI, and then pass it along to
     * the next step in the creation wizard.
     */
    private void updateSaluteReportAndPassOn()
    {
        final EditText activityEditText = view.findViewById(R.id.editText);
        saluteReport.setActivity(activityEditText.getText().toString());

        final SecondFragmentActivityDirections.ActionActivityToLocation action =
                SecondFragmentActivityDirections.actionActivityToLocation(saluteReport);

        NavHostFragment.findNavController(SecondFragmentActivity.this).navigate(action);
    }
}
