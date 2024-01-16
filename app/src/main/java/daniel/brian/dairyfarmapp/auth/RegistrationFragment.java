package daniel.brian.dairyfarmapp.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.db.AuthenticationDB;
import daniel.brian.dairyfarmapp.employee.EmployeeActivity;

public class RegistrationFragment extends Fragment {
    AuthenticationDB authDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        // Accessing all the views
        EditText firstName = view.findViewById(R.id.firstName);
        EditText lastName = view.findViewById(R.id.lastName);
        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        Button btnRegister = view.findViewById(R.id.btnRegister);

        // Declaring the database
        authDB = new AuthenticationDB(this.getContext());

        //Navigating to the Login Fragment
        TextView regToLogin = view.findViewById(R.id.regToLogin);
        regToLogin.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_registrationFragment_to_loginFragment);
        });

        // Registering a new user
        btnRegister.setOnClickListener(view1 -> {
            String firstname = firstName.getText().toString();
            String lastname = lastName.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if(firstname.isEmpty() || lastname.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()){
                Snackbar.make(requireView(),"Please Enter All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                if(userEmail.contains("@gmail.com") || userEmail.contains("@yahoo.com")){
                    boolean registerUser = authDB.registerUser(firstname,lastname,userEmail,userPassword);
                    if(registerUser){
                        Snackbar.make(requireView(),"Yaay!! Registration Successful!",Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(this.getContext(), EmployeeActivity.class);
                        startActivity(intent);
                    }else{
                        Snackbar.make(requireView(),"Oops!! Registration Failed!. User already exists!",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(requireView(),"Oops!! Please Enter a valid Email!",Snackbar.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }
}