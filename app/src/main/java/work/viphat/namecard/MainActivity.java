package work.viphat.namecard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import static android.provider.MediaStore.Images;

public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private String avatarPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadPicture(View view){
        Intent i = new Intent(Intent.ACTION_PICK,
                    Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void generateOutput(View view){

        TextView tvName = (TextView) findViewById(R.id.strUserName);
        String name = tvName.getText().toString();
        String education = "";

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioEducation);
        RadioButton e1 = (RadioButton) findViewById(R.id.radioEducation1);
        RadioButton e2 = (RadioButton) findViewById(R.id.radioEducation2);
        RadioButton e3 = (RadioButton) findViewById(R.id.radioEducation3);

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == e1.getId()) {
            education = e1.getText().toString();
        } else if (selectedId == e2.getId()) {
            education = e2.getText().toString();
        } else {
            education = e3.getText().toString();
        }

        Spinner spinnerField = (Spinner) findViewById(R.id.spinnerField);

        TextView tvWorkPlace = (TextView) findViewById(R.id.strWorkPlace);

        TextView tvAddress = (TextView) findViewById(R.id.strAddress);

        TextView tvEmail = (TextView) findViewById(R.id.strEmail);

        TextView tvPhone = (TextView) findViewById(R.id.strPhone);

        Intent i = new Intent(MainActivity.this, NameCardActivity.class);
        i.putExtra("name", education + " " + name);
        i.putExtra("avatarPath",avatarPath);
        i.putExtra("field", spinnerField.getSelectedItem().toString());
        i.putExtra("workPlace", tvWorkPlace.getText().toString());
        i.putExtra("address", tvAddress.getText().toString());
        i.putExtra("email", tvEmail.getText().toString());
        i.putExtra("phone", tvPhone.getText().toString());
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            avatarPath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgAvatar);
            imageView.setImageBitmap(BitmapFactory.decodeFile(avatarPath));

        }

    }


}
