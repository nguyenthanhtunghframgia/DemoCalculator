package com.example.nguyenthanhtungh.myapplication.ui.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenthanhtungh.myapplication.R;

public class MainFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = MainFragment.class.getName();
    private static final String KEY_PREF = "preftext";
    private static final String KEY_STATE = "statetext";

    private View view;
    private SharedPreferences preferences;
    private TextView textInputResult;

    private String mRawText;
    private int[] arrIdButton = {R.id.button_clear, R.id.button_sum_minus, R.id.button_percent,
            R.id.button_percent, R.id.button_divide, R.id.button_number_7, R.id.button_number_8,
            R.id.button_number_9, R.id.button_number_4, R.id.button_number_5, R.id.button_number_6,
            R.id.button_number_1, R.id.button_number_2, R.id.button_number_3, R.id.button_number_0,
            R.id.button_multi, R.id.button_minus, R.id.button_sum, R.id.button_calc, R.id.button_dot};

    private String mOperator;
    private Float mLeftVal;
    private Float mRightVal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mRawText = savedInstanceState.getString(KEY_STATE);
        } else {
            preferences = getContext().getSharedPreferences("backup", Context.MODE_PRIVATE);
            mRawText = preferences.getString(KEY_PREF, "");
        }
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        textInputResult = (TextView) view.findViewById(R.id.text_input_result);
        textInputResult.setText(mRawText);

        for (int i = 0; i < arrIdButton.length; i++) {
            View v = view.findViewById(arrIdButton[i]);
            v.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_clear:
                doClear();
                doUpdateButton(false);
                break;
            case R.id.button_sum_minus:
                mOperator = "-";
                doUpdateButton(true);
                doUpdateTextView("-");
                break;
            case R.id.button_percent:
                mOperator = "-";
                doUpdateButton(true);
                doUpdateTextView("%");
                break;
            case R.id.button_divide:
                mOperator = "/";
                doUpdateButton(true);
                doUpdateTextView("/");
                break;
            case R.id.button_multi:
                mOperator = "x";
                doUpdateButton(true);
                doUpdateTextView("x");
                break;
            case R.id.button_minus:
                mOperator = "-";
                doUpdateButton(true);
                doUpdateTextView("-");
                break;
            case R.id.button_sum:
                mOperator = "+";
                doUpdateButton(true);
                doUpdateTextView("+");
                break;
            case R.id.button_calc:
                doCalc();
                doUpdateButton(false);
                break;
            default:
                doUpdateButton(false);
                doUpdateTextView(((Button) view).getText().toString());
                break;
        }
    }

    private void doCalc() {
        for (int i = 0; i < mRawText.length(); i++) {
            String s = "";
            if (mRawText.charAt(i) >= '0' && mRawText.charAt(i) <= '9') {
                s = s.concat(Character.toString(mRawText.charAt(i)));
            } else {
                break;
            }
            mLeftVal = Float.parseFloat(s);

        }
        for (int i = mRawText.length() - 1; i >= 0; i--) {
            String s = "";
            if (mRawText.charAt(i) >= '0' && mRawText.charAt(i) <= '9') {
                s = s.concat(Character.toString(mRawText.charAt(i)));
            } else {
                break;
            }
            mRightVal = Float.parseFloat(s);
        }
        if (mOperator == "+") {
            mRawText = String.valueOf(mLeftVal + mRightVal);
            textInputResult.setText(mRawText);
        }
        if (mOperator == "-") {
            mRawText = String.valueOf(mLeftVal - mRightVal);
            textInputResult.setText(mRawText);
        }
        if (mOperator == "x") {
            mRawText = String.valueOf(mLeftVal * mRightVal);
            textInputResult.setText(mRawText);
        }
        if (mOperator == "/") {
            mRawText = String.valueOf(mLeftVal / mRightVal);
            textInputResult.setText(mRawText);
        }
        if (mOperator == "%") {
            mRawText = String.valueOf((mLeftVal * mRightVal) / 100);
            textInputResult.setText(mRawText);
        }
        mOperator = "";
        mLeftVal = 0.0f;
        mRightVal = 0.0f;
        mRawText = "";
    }

    private void doUpdateButton(boolean isDisable) {
        if (isDisable) {
            view.findViewById(R.id.button_sum).setEnabled(false);
            view.findViewById(R.id.button_minus).setEnabled(false);
            view.findViewById(R.id.button_multi).setEnabled(false);
            view.findViewById(R.id.button_divide).setEnabled(false);
            view.findViewById(R.id.button_percent).setEnabled(false);
            view.findViewById(R.id.button_sum_minus).setEnabled(false);
        } else {
            view.findViewById(R.id.button_sum).setEnabled(true);
            view.findViewById(R.id.button_minus).setEnabled(true);
            view.findViewById(R.id.button_multi).setEnabled(true);
            view.findViewById(R.id.button_divide).setEnabled(true);
            view.findViewById(R.id.button_percent).setEnabled(true);
            view.findViewById(R.id.button_sum_minus).setEnabled(false);
        }
    }

    private void doUpdateTextView(String textUpdate) {
        mRawText = mRawText.concat(textUpdate);
        textInputResult.setText(mRawText);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                doClear();
                break;
            case R.id.menu_save_last_result:
                doSaveResult();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doClear() {
        mRawText = "";
        textInputResult.setText(mRawText);
        doUpdatePreference("");
        Toast.makeText(getContext(), R.string.clear_text_noti, Toast.LENGTH_SHORT).show();
    }

    private void doSaveResult() {
        doUpdatePreference(textInputResult.getText().toString());
        Toast.makeText(getContext(), R.string.save_text_noti, Toast.LENGTH_SHORT).show();
    }

    private void doUpdatePreference(String saveTextContent) {
        preferences = getContext().getSharedPreferences("backup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PREF, saveTextContent);
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_STATE, textInputResult.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
