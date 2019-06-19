package edu.polytech.busyholiday3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;


public class nomembers extends Activity {

    ViewFlipper flipper;

    ToggleButton toggle_Flipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomembers);

        flipper= (ViewFlipper)findViewById(R.id.flipper);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        toggle_Flipping= (ToggleButton)findViewById(R.id.toggle_auto);
        toggle_Flipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){//On 으로 바뀌었으므로 ..자동 Flipping 시작..
                    //1초의 간격으로 ViewFlipper의 View 자동 교체
                    flipper.setFlipInterval(4000);//플리핑 간격(4000ms)
                    flipper.startFlipping();//자동 Flipping 시작

                }else{//OFF로 변경되었으므로  Flipping 정지
                    flipper.stopFlipping();;//Flipping 정지
                }
            }
        });
    }
    //onClick속성이 지정된 View가 클릭되었을 때 자동으로 호출되는 메소드.
    public void mOnClick(View v){
        switch( v.getId() ){
            case R.id.btn_previous:
                flipper.showPrevious();//이전 View로 교체
                break;
            case R.id.btn_next:
                flipper.showNext();//다음 View로 교체
                break;
        }
    }
}

//    Button bt1,bt2,bt3,bt4;
//    ImageView iv;
// for loop
//        for (int image: images) {
//            flipperImages(image);
//        }
//        bt1 = (Button)findViewById(R.id.NMButton1);
//        bt2 = (Button)findViewById(R.id.NMButton2);
//        bt3 = (Button)findViewById(R.id.NMButton3);
//        bt4 = (Button)findViewById(R.id.NMButton4);
//
//        bt1.setOnClickListener(this);
//        bt2.setOnClickListener(this);
//        bt3.setOnClickListener(this);
//        bt4.setOnClickListener(this);
//        flipperImages(R.drawable.slide1);
//        iv = (ImageView)findViewById(R.drawable.slide1);
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.NMButton1:
//                flipperImages(R.drawable.slide1);
//
//                break;
//
//            case R.id.NMButton2:
//                flipperImages(R.drawable.slide2);
//
//                break;
//
//            case R.id.NMButton3:
//                flipperImages(R.drawable.slide3);
//
//                break;
//
//            case R.id.NMButton4:
//                flipperImages(R.drawable.slide4);
//
//                break;
//
//        }
//
//    }
//    public void flipperImages(int image) {
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource(image);
//
//        v_flipper.addView(imageView);
//        v_flipper.setFlipInterval(4000);
//        v_flipper.setAutoStart(true);
//
//        //animation
//        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
//        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
//    }
//}