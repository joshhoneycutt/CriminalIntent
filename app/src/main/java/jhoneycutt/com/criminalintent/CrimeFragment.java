package jhoneycutt.com.criminalintent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.CheckBox;
import android.widget.Button;
import java.util.UUID;

/**
 * Created by Josh on 7/28/16.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.joshhoneycutt.android.criminalintent.crime_id";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCrime = new Crime();
        UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //This space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }

            });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getmDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Set the crime's solved property
                mCrime.setmSolved(isChecked);
            }
        });

        return v;
    }
}
