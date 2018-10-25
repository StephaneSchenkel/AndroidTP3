package test.test.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListStudentAdapter extends ArrayAdapter<Student> {

    private List<Student> list;
    private Context context;
    private int resource;

    private String typeAffichage;

    public ListStudentAdapter(@NonNull Context context, int resource, List<Student> objects, String affichage) {
        super(context, resource, objects);
        list = objects;
        this.context = context;
        this.resource = resource;
        typeAffichage = affichage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }

        if(list != null) {
            Student student = list.get(position);
            if (student != null) {
                TextView fullname = (TextView) v.findViewById(R.id.FullName);

                if (fullname != null) {
                    if(typeAffichage.equals("np"))
                        fullname.setText(student.getLastname() + " " + student.getFirstname());
                    else if(typeAffichage.equals("pn"))
                        fullname.setText(student.getFirstname() + " " + student.getLastname());
                }
            }
        }

        return v;
    }

    public String getTypeAffichage() {
        return typeAffichage;
    }

    public void setTypeAffichage(String typeAffichage) {
        this.typeAffichage = typeAffichage;
    }
}
