package th.ac.ssru.kaewruamchor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SSRU on 10/1/2561.
 */

public class EducationAdapter extends ArrayAdapter<Education> {

    private Context context;
    private int itemLayoutId;
    private Education educations[] = null;

    public EducationAdapter(Context context, int itemLayoutId, Education[] educations) {
        super(context, itemLayoutId, educations);

        this.itemLayoutId = itemLayoutId;
        this.context = context;
        this.educations = educations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(itemLayoutId, parent, false);

        ImageView ivLogo = item.findViewById(R.id.ivLogo);
        TextView tvFaculty = item.findViewById(R.id.tvFaculty);
        TextView tvMajor = item.findViewById(R.id.tvMajor);
        TextView tvDegree = item.findViewById(R.id.tvDegree);

        Education education = educations[position];
        byte[] decodedString = Base64.decode(education.getLogo(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ivLogo.setImageBitmap(decodedByte);
        tvFaculty.setText(education.getFaculty());
        tvMajor.setText(education.getMajor());
        tvDegree.setText(education.getDegree());


        return item;
    }
}
