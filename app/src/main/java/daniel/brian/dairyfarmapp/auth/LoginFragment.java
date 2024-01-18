package daniel.brian.dairyfarmapp.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.activities.AdminHomeActivity;
import daniel.brian.dairyfarmapp.db.AuthenticationDB;
import daniel.brian.dairyfarmapp.employee.EmployeeActivity;


public class LoginFragment extends Fragment {
    AuthenticationDB authDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Accessing the views
        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        CheckBox asAdmin = view.findViewById(R.id.asAdmin);
        CheckBox asEmployee = view.findViewById(R.id.asEmployee);

        //Navigating to the register Fragment
        TextView loginToReg = view.findViewById(R.id.loginToRegister);
        loginToReg.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_loginFragment_to_registrationFragment);
        });

        //Navigating to the forgot password Fragment
        TextView forgotPass = view.findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment);
        });

        // Declaring the database
        authDB = new AuthenticationDB(this.getContext());

        // Login a register user
        btnLogin.setOnClickListener(view1 -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            boolean checkAdmin = asAdmin.isChecked();
            boolean checkEmployee = asEmployee.isChecked();

            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Snackbar.make(requireView(),"Please Enter All Fields!",Snackbar.LENGTH_LONG).show();
            }else{
               if(userEmail.matches("admin@dfs.com") && userPassword.matches("admin@dfs")){
                   if(checkAdmin && !checkEmployee){
                       asEmployee.isTemporarilyDetached();
                       Intent intent = new Intent(this.getContext(), AdminHomeActivity.class);
                       startActivity(intent);
                       Snackbar.make(requireView(),"Logging as Admin Successful!",Snackbar.LENGTH_LONG).show();
                   }else{
                       Snackbar.make(requireView(),"Oops!! Check the right box!",Snackbar.LENGTH_LONG).show();
                   }
               }else{
                   if(checkEmployee && !checkAdmin){
                       asAdmin.isTemporarilyDetached();
                       if(userEmail.contains("@gmail.com") || userEmail.contains("@yahoo.com")){
                           boolean loginUser = authDB.loginUser(userEmail,userPassword);
                           if(loginUser){
                               Intent intent = new Intent(this.getContext(), EmployeeActivity.class);
                               startActivity(intent);
                               Snackbar.make(requireView(),"Login Successful!",Snackbar.LENGTH_LONG).show();
                           }else{
                               Snackbar.make(requireView(),"Invalid Credentials! Try Again!",Snackbar.LENGTH_LONG).show();
                           }
                       }else {
                           Snackbar.make(requireView(),"Oops!! Please Enter a valid Email!",Snackbar.LENGTH_LONG).show();
                       }
                   }else{
                       Snackbar.make(requireView(),"Oops!! Check the right box!",Snackbar.LENGTH_LONG).show();
                   }
               }
            }
        });

        return view;
    }
}