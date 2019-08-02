package com.chen.app_ec.Ec_core_content;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.app_core.app.BaseFragment;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.newmyfood.DialogFoodfragment;

import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends BaseFragment {

    @BindView(R2.id.tv_edit)
    TextView tv_edit;

    @BindView(R2.id.et_name)
    EditText et_name;
    @BindView(R2.id.et_sex)
    TextView et_sex;

    @BindView(R2.id.et_high)
    EditText et_high;

    @BindView(R2.id.et_age)
    EditText et_age;

    @BindView(R2.id.et_weight)
    EditText et_weight;

    @BindView(R2.id.tv_scoreofhelath)
    TextView tv_score;

    @BindView(R2.id.cir_img)
    CircleImageView mCircleImageView;

    private static final int TAKE_PHOTO_PERMISSION_REQUEST_CODE = 0; // 拍照的权限处理返回码
    private static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1; // 读储存卡内容的权限处理返回码

    private static final int TAKE_PHOTO_REQUEST_CODE = 3; // 拍照返回的 requestCode
    private static final int CHOICE_FROM_ALBUM_REQUEST_CODE = 4; // 相册选取返回的 requestCode
    private static final int CROP_PHOTO_REQUEST_CODE = 5; // 裁剪图片返回的 requestCode

    private Uri photoUri = null;
    private Uri photoOutputUri = null; // 图片最终的输出文件的 Uri



    @OnClick(R2.id.cir_img)
    void onclic(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请读写内存卡内容的权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_SDCARD_PERMISSION_REQUEST_CODE);
        }

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //下面是对调用相机拍照权限进行申请
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,}, TAKE_PHOTO_PERMISSION_REQUEST_CODE);
        } else {
            startCamera();
        }
    }


    /**
     * 拍照
     */
    private void startCamera() {
        /*
         * 设置拍照得到的照片的储存目录，因为我们访问应用的缓存路径并不需要读写内存卡的申请权限，
         * 因此，这里为了方便，将拍照得到的照片存在这个缓存目录中
         */
        File file = new File(getActivity().getExternalCacheDir(), "image.jpg");
        try {
            if(file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * 因 Android 7.0 开始，不能使用 file:// 类型的 Uri 访问跨应用文件，否则报异常，
         * 因此我们这里需要使用内容提供器，FileProvider 是 ContentProvider 的一个子类，
         * 我们可以轻松的使用 FileProvider 来在不同程序之间分享数据(相对于 ContentProvider 来说)
         */
        if(Build.VERSION.SDK_INT >= 24) {
            photoUri = FileProvider.getUriForFile(getActivity(), "com.zhi_dian.provider", file);
        } else {
            photoUri = Uri.fromFile(file); // Android 7.0 以前使用原来的方法来获取文件的 Uri
        }
        // 打开系统相机的 Action，等同于："android.media.action.IMAGE_CAPTURE"
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 设置拍照所得照片的输出目录
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST_CODE);
    }

    private void cropPhoto(Uri inputUri) {
        // 调用系统裁剪图片的 Action
        Intent cropPhotoIntent = new Intent("com.android.camera.action.CROP");
        // 设置数据Uri 和类型
        cropPhotoIntent.setDataAndType(inputUri, "image/*");
        // 授权应用读取 Uri，这一步要有，不然裁剪程序会崩溃
        cropPhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 设置图片的最终输出目录
        cropPhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                photoOutputUri = Uri.parse("file:////sdcard/image_output.jpg"));
        startActivityForResult(cropPhotoIntent, CROP_PHOTO_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            // 通过返回码判断是哪个应用返回的数据
            switch (requestCode) {
                // 拍照
                case TAKE_PHOTO_REQUEST_CODE:
                    cropPhoto(photoUri);
                    break;
                // 相册选择
                case CHOICE_FROM_ALBUM_REQUEST_CODE:
                    cropPhoto(data.getData());
                    break;
                // 裁剪图片
                case CROP_PHOTO_REQUEST_CODE:
                    File file = new File(photoOutputUri.getPath());
                    if(file.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(photoOutputUri.getPath());
                        mCircleImageView.setImageBitmap(bitmap);
//                        file.delete(); // 选取完后删除照片
                    } else {
                        Toast.makeText(getActivity(), "找不到照片", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }



    boolean isedit = false;





    @OnClick(R2.id.et_sex)
    void onclick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择性别");
        final String[] sex = {"男","女"};
        builder.setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              et_sex.setText( sex[i] );
            }
        });
        builder.create().show();

    }

    @OnClick(R2.id.tv_edit)
    void edit(){
        if (isedit){
            isedit =false;
            tv_edit.setText("编辑");
            et_high.setEnabled(false);
            et_weight.setEnabled(false);
            et_sex.setEnabled(false);
            et_age.setEnabled(false);
            //TODO 网络请改变用户信息
        }else {
            isedit = true;
            tv_edit.setText("保存");
            et_high.setEnabled(true);
            et_weight.setEnabled(true);
            et_sex.setEnabled(true);
            et_age.setEnabled(true);
        }
    }



    @Override
    public void bindview(Bundle savedInstance, View rootview){
        setTextWathcer();
        //TODO 获取用户信息
        int high = Integer.valueOf(et_high.getText().toString()) ;
        int weight = Integer.valueOf(et_weight.getText().toString());
        tv_score.setText(getBMI(high,weight));


    }

    private String getBMI(int high,int weight){
        double bmi = weight /(high *high);
        String result;
        if (bmi > 28){
            result =  "偏瘦";
        } else if (bmi > 24) {
            result ="过重";
        }else if (bmi >18.4){
            result ="正常";
        }else {
            result = "偏瘦";
        }
        return result;
    }


    @Override
    public Object setlayout() {
        return R.layout.fragment_me;
    }






    private void setTextWathcer(){
        et_age.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int age = Integer.valueOf(charSequence.toString());
                if (age>=120){
                    et_age.setText("120");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        et_high.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int high = Integer.valueOf(charSequence.toString());
                if (high >= 220){
                    et_age.setText("220");
                }
                if (high <= 80){
                    et_high.setText("100");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int weight = Integer.valueOf(charSequence.toString());
                if (weight >= 200){
                    et_age.setText("200");
                }
                if (weight <= 20){
                    et_high.setText("20");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

}
