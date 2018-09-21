package test.test.tp3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StudentInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_info, container, false);

        if(MainActivity.SelectedStudent != null){
            TextView lastname = (TextView)view.findViewById(R.id.lastnameinfo);
            TextView firstname = (TextView)view.findViewById(R.id.firstnameinfo);
            TextView sex = (TextView)view.findViewById(R.id.sexinfo);
            TextView group = (TextView)view.findViewById(R.id.groupinfo);
            TextView redoublant = (TextView)view.findViewById(R.id.redoublantinfo);
            TextView birthday = (TextView)view.findViewById(R.id.birthdayinfo);
            TextView email = (TextView)view.findViewById(R.id.emailinfo);

            lastname.setText(MainActivity.SelectedStudent.getLastname());
            firstname.setText(MainActivity.SelectedStudent.getFirstname());
            sex.setText(MainActivity.SelectedStudent.getSexe());
            group.setText(MainActivity.SelectedStudent.getGroup());
            redoublant.setText(MainActivity.SelectedStudent.isRedoublant() ? "Oui" : "Non");
            birthday.setText(MainActivity.DATE_FORMAT.format(MainActivity.SelectedStudent.getBirthday()));
            email.setText(MainActivity.SelectedStudent.getEmail());
        }

        return view;
    }
}
