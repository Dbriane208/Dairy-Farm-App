package daniel.brian.dairyfarmapp.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import daniel.brian.dairyfarmapp.R;
import daniel.brian.dairyfarmapp.databinding.FragmentForgotPasswordBinding;
import daniel.brian.dairyfarmapp.db.AuthenticationDB;

public class ForgotPasswordFragment extends Fragment {
   FragmentForgotPasswordBinding forgotPasswordBinding;
   AuthenticationDB authenticationDB;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        forgotPasswordBinding = FragmentForgotPasswordBinding.inflate(getLayoutInflater());

        // initializing the database
        authenticationDB = new AuthenticationDB(this.getContext());

        // setting the onclick
        forgotPasswordBinding.updatePass.setOnClickListener(view -> {
            String email = Objects.requireNonNull(forgotPasswordBinding.email.getText()).toString();
            String password = Objects.requireNonNull(forgotPasswordBinding.password.getText()).toString();

            if(email.isEmpty() || password.isEmpty()){
                Snackbar.make(requireView(),"Please Enter All Fields",Snackbar.LENGTH_LONG).show();
            } else{
                boolean updatePass = authenticationDB.UpdatePassword(email,password);
                if(updatePass){
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_forgotPasswordFragment_to_loginFragment);
                    Snackbar.make(requireView(),"Password Updated Successfully!",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(requireView(),"Password Updated Successfully!",Snackbar.LENGTH_LONG).show();
                }
            }
        });



        return forgotPasswordBinding.getRoot();
    }
}