package piw.rmutsv.ac.th.firebaseservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import piw.rmutsv.ac.th.firebaseservice.R;

/**
 * Created by RTP-10305 on 6/2/2561.
 */

public class RegisterFragment extends Fragment {

    //Intial value
    private String nameString, emailString,passwordString;
   private ImageView ImvSave;
    private FirebaseAuth firebaseAuth;
    @Nullable


   //setup Button

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        saveValueToFirebase();

    }

    private void saveValueToFirebase (){


       ImvSave = getView().findViewById(R.id.imvSave);

        ImvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    //Success
                                    Toast.makeText(getActivity(),"Register Success",Toast.LENGTH_LONG).show();


                                }
                                else {
                                    Toast.makeText(getActivity(),"!!!No register!!!",Toast.LENGTH_LONG).show();

                                }
                            }
                        });


            }
        });




    }//End Method saveValueToFirebase

//end setup button

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        return view;
    }
}
