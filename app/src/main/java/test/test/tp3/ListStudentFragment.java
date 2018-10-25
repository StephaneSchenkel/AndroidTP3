package test.test.tp3;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.test.tp3.db.StudentHelper;
import test.test.tp3.db.StudentReaderContract;

public class ListStudentFragment extends Fragment {

    private ListStudentAdapter adapter;
    private ListView listView;
    private StudentHelper helper;

    public static ListStudentFragment newInstance(StudentHelper helper, String affichage) {
        ListStudentFragment f = new ListStudentFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putSerializable("Helper", helper);
        args.putString("Affichage", affichage);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(MainActivity.List == null)
            MainActivity.List = new ArrayList<>();

        Bundle args = getArguments();
        helper = (StudentHelper)args.getSerializable("Helper");
        String affichage = args.getString("Affichage");

        adapter = new ListStudentAdapter(inflater.getContext(), R.layout.list_student_row, MainActivity.List, affichage);

        View view = inflater.inflate(R.layout.list_student, container, false);

        listView = (ListView)view.findViewById(R.id.listStudent);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student)listView.getItemAtPosition(position);

                MainActivity.SelectedStudent = student;

                LaunchStudentInfo();
            }
        });

        return view;
    }

    private void LaunchStudentInfo(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        StudentInfoFragment infoFragment = new StudentInfoFragment();
        transaction.replace(R.id.infoFragment, infoFragment);
        transaction.commitAllowingStateLoss();
    }

    public void AddStudent(Student student){
        if(MainActivity.List == null)
            MainActivity.List = new ArrayList<>();

        MainActivity.List.add(student);

        helper.AddStudent(student);

        if(adapter != null)
            adapter.notifyDataSetChanged();
    }

    public void DeleteCurrentStudent(){
        new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete student")
                .setMessage("Voulez-vous vraiment supprimer cet étudiant ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.DeleteStudent(MainActivity.SelectedStudent);
                        MainActivity.List.remove(MainActivity.SelectedStudent);
                        MainActivity.SelectedStudent = null;

                        LaunchStudentInfo();

                        if(adapter != null)
                            adapter.notifyDataSetChanged();
                    }

                })
                .setNegativeButton("Non", null)
                .show();
    }

    public void UpdatePref(String pref){
        adapter.setTypeAffichage(pref);
        adapter.notifyDataSetChanged();
    }
}
