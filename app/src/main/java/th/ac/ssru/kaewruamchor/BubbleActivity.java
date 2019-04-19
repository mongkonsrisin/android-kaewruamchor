package th.ac.ssru.kaewruamchor;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.maps.GoogleMap;
import com.google.gson.Gson;
import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.nio.IntBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import th.ac.ssru.kaewruamchor.Dao.Student;

public class BubbleActivity extends AppCompatActivity {
    BubblePicker picker;
    SSRU ssru;
    ShareDialog shareDialog;
    List<Student> students;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share, menu);

        return true;
    }

    public static String MD5_Hash(String s) {
        MessageDigest m = null;
        s="EzjSsRuFrd2O18pD"+s;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.actionShare) {
            share();
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("แก้วรวมช่อ 80 ปี")
                    .setContentDescription("แก้วรวมช่อ 80 ปี")
                    .setContentUrl(Uri.parse("https://reg.ssru.ac.th/ssru80th/facebooksharefriends.php/"+ssru.getStuId()+"/"+MD5_Hash(ssru.getStuId())))
                    .build();

            shareDialog.show(linkContent);
        }    }

    /*private  void share() {
        Bitmap bitmap = takeScreenShot(this);

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(content);
        }
*/



    public Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //Find the screen dimensions to create bitmap in the same size.
        int width = activity.getWindowManager().getDefaultDisplay(). getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        picker = (BubblePicker) findViewById(R.id.picker);
        picker.setBubbleSize(1);
        ssru = (SSRU)getApplicationContext();
        students = ssru.getStudents();
        String value = String.valueOf(students.size());

































        shareDialog = new ShareDialog(this);

        picker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return students.size();
            }

            @NotNull
            @Override
            public PickerItem getItem(int position) {
                PickerItem item = new PickerItem();
                item.setTitle(students.get(position).getStuId());
                item.setTextSize(0.0f);
                item.setTextColor(Color.parseColor("#ffffff"));
                byte[] decodedString = Base64.decode(students.get(position).getStuPhoto(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Drawable d = new BitmapDrawable(getResources(), decodedByte);
                item.setOverlayAlpha(0);
                item.setBackgroundImage(d);
                item.setSelected(true);
              //  item.setIcon(d);
                return item;
            }
        });

        picker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(PickerItem pickerItem) {

            }

            @Override
            public void onBubbleDeselected(PickerItem pickerItem) {
                Intent intent = new Intent(BubbleActivity.this,FriendProfileActivity.class);
                String id = pickerItem.getTitle();
                intent.putExtra("id",id);
               startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        picker.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        picker.onResume();
    }


}