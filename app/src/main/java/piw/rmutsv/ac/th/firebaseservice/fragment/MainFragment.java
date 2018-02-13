package piw.rmutsv.ac.th.firebaseservice.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import piw.rmutsv.ac.th.firebaseservice.R;

/**
 * Created by RTP-10305 on 4/2/2561.
 */

public class MainFragment extends Fragment {

    private String emailString, passwordString;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       //Register Controller
        registerCntroller();

        //Login Controller
        loginController();
    }

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                emailString = emailEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();


                    checkEmailAndPass();


            }
        });
    }

    private void checkEmailAndPass() {


        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait Few Minus ...");
        progressDialog.show();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailString,passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            SignIn Success
                            Toast.makeText(getActivity(), "Welcome To my Service",
                                    Toast.LENGTH_SHORT).show();


                        } else {
//                            Cannot SignIn
                            Toast.makeText(getActivity(), "Connot SignIn",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private void registerCntroller() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment,new RegisterFragment())
                        .addToBackStack(null).commit();
            }
        });
    }//Method


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main,container,false);
    return view;
    }

}//end
