package com.demo.hp.customercamera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.demo.hp.customercamera.camera.CameraSurfaceView;
import com.demo.hp.customercamera.camera.RectOnCamera;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,RectOnCamera.IAutoFocus{

    private CameraSurfaceView mCameraSurfaceView;
    private RectOnCamera mRectOnCamera;
    private Button takePicBtn;

    private boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.cameraSurfaceView);
        mRectOnCamera = (RectOnCamera) findViewById(R.id.rectOnCamera);
        takePicBtn= (Button) findViewById(R.id.takePic);
        mRectOnCamera.setIAutoFocus(this);
        takePicBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePic:
                mCameraSurfaceView.takePicture();
                break;
            default:
                break;
        }
    }


    @Override
    public void autoFocus() {
        mCameraSurfaceView.setAutoFocus();
    }
}
