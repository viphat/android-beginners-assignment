package work.viphat.namecard;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NameCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_card);
        Bundle bundle = this.getIntent().getExtras();

        TextView tvName = (TextView) findViewById(R.id.labelUserName);
        tvName.setText(bundle.getString("name"));

        TextView tvField = (TextView) findViewById(R.id.labelField);
        tvField.setText(bundle.getString("field"));

        TextView tvWorkPlace = (TextView) findViewById(R.id.labelWorkplace);
        tvWorkPlace.setText(bundle.getString("workPlace"));


        TextView tvAddress = (TextView) findViewById(R.id.labelAddress);
        tvAddress.setText(bundle.getString("address"));

        TextView tvEmail = (TextView) findViewById(R.id.labelEmail);
        tvEmail.setText(bundle.getString("email"));

        TextView tvPhone = (TextView) findViewById(R.id.labelPhone);
        tvPhone.setText(bundle.getString("phone"));

        ImageView imageView = (ImageView) findViewById(R.id.imgAvatar);
        imageView.setImageBitmap(BitmapFactory.decodeFile(bundle.getString("avatarPath")));


    }

}
