package com.yneed.yneed;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class Yneedapp extends Activity implements OnClickListener {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private List<View> mViews = new ArrayList<View>();
    // TAB

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;

    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;

    private MyImgScroll myPager; // 图片容器
    private LinearLayout ovalLayout; // 圆点容器
    private List<View> listViews; // 图片组
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    private EditText mEtSearch = null;// 输入搜索内容
    private Button mBtnClearSearchText = null;// 清空搜索信息的按钮
    private LinearLayout mLayoutClearSearchText = null;
    private LinearLayout searchlayout;

    Button mzhuce_button;
    String APPKEY="f968933685b2";
    String appserete="c88087a41d85e7f2174c1d1b170062be";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化
        SMSSDK.initSDK(this, APPKEY, appserete);
        //配置信息
        mzhuce_button= (Button) this.findViewById(R.id.zhuce);//绑定注册按钮
        //设置点击事件
        mzhuce_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册手机号
                RegisterPage registerPage = new RegisterPage();
                //注册回调事件
                registerPage.setRegisterCallback(new EventHandler() {
                    //事件完成后调用
                    public void afterEvent(int event, int result, Object data) {
                        //判断结果是否已经完成
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //获取data
                            HashMap<String, Object> maps = (HashMap<String, Object>) data;
                            //国家
                            String country = (String) maps.get("country");
                            //手机号
                            String phone = (String) maps.get("phone");

                            submitUserInfo(country,phone);
                        }
                    }

                });
                //显示注册界面
                registerPage.show(Yneedapp.this);

            }
        });
        initView();

        initEvents();
        myPager = (MyImgScroll) findViewById(R.id.mvp);
        ovalLayout = (LinearLayout) findViewById(R.id.vb);
        InitViewPager();//初始化图片
        //开始滚动
        myPager.start(this, listViews, 4000, ovalLayout,
                R.layout.ad_botton_item, R.id.ad_item_v,
                R.drawable.dot_focused, R.drawable.dot_normal);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        searchlayout = (LinearLayout) findViewById(R.id.serarch_layout);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mBtnClearSearchText = (Button) findViewById(R.id.btn_clear_search_text);
        mLayoutClearSearchText = (LinearLayout) findViewById(R.id.layout_clear_search_text);
        mEtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int textLength = mEtSearch.getText().length();
                if (textLength > 0) {
                    mLayoutClearSearchText.setVisibility(View.VISIBLE);
                } else {
                    mLayoutClearSearchText.setVisibility(View.GONE);
                }
            }
        });

        mBtnClearSearchText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
                mLayoutClearSearchText.setVisibility(View.GONE);
            }
        });
        mEtSearch.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(View arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(Yneedapp.this,
                            mEtSearch.getText().toString().trim(),
                            Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

}

    /**
     *提交用户信息
     * @param country
     * @param phone
     */

    public void submitUserInfo(String country,String phone) {
        Random r=new Random();

        String uid=Math.abs(r.nextInt())+"";
        String nickName="Yneed";
        SMSSDK.submitUserInfo(uid,nickName,null,country,phone);
    }
    private void initEvents() {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                resetImg();
                switch (currentItem) {
                    case 0:
                        mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        mAddressImg
                                .setImageResource(R.drawable.tab_address_pressed);
                        break;


                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        // tabs
        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);

        // ImageButton
        mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);


        LayoutInflater mInflater = LayoutInflater.from(this);
        View tab01 = mInflater.inflate(R.layout.tab01, null);
        View tab02 = mInflater.inflate(R.layout.tab02, null);
        View tab03 = mInflater.inflate(R.layout.tab03, null);
        mViews.add(tab01);
        mViews.add(tab02);
        mViews.add(tab03);

        mAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return mViews.size();
            }
        };

        mViewPager.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                mViewPager.setCurrentItem(0);
                mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                myPager.setVisibility(View.VISIBLE);  //设置滚动图片可见
                ovalLayout.setVisibility(View.VISIBLE);//设置进度小圆点可见
                searchlayout.setVisibility(View.VISIBLE);//设置进度s搜索栏可见
                break;
            case R.id.id_tab_frd:
                mViewPager.setCurrentItem(1);
                mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                myPager.setVisibility(View.GONE); //设置滚动图片隐藏
                ovalLayout.setVisibility(View.GONE);//设置进度小圆点隐藏
                searchlayout.setVisibility(View.GONE);
                break;
            case R.id.id_tab_address:
                mViewPager.setCurrentItem(2);
                mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                myPager.setVisibility(View.GONE);
                ovalLayout.setVisibility(View.GONE);
                searchlayout.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }


    /**
     * 将所有的图片切换为暗色的
     */
    private void resetImg() {
        mWeixinImg.setImageResource(R.drawable.tab_weixin_normal);
        mFrdImg.setImageResource(R.drawable.tab_find_frd_normal);
        mAddressImg.setImageResource(R.drawable.tab_address_normal);
    }

    @Override
    protected void onRestart() {
        myPager.startTimer();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        myPager.stopTimer();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Yneedapp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.yneed.yneed/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    private void InitViewPager() {
        listViews = new ArrayList<View>();
        int[] imageResId = new int[]{R.drawable.banner1, R.drawable.banner2,
                R.drawable.banner3, R.drawable.d, R.drawable.banner4};
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {// 设置图片点击事件
                    Toast.makeText(Yneedapp.this,
                            "点击了:" + myPager.getCurIndex(), Toast.LENGTH_SHORT)
                            .show();
                }
            });
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            listViews.add(imageView);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Yneedapp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.yneed.yneed/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }
}






// 滚动图适配器
class  MyPagerAdapter  extends PagerAdapter {
    private Activity mActivity; // 上下文
    private List<View> mListViews; // 图片组
    public MyPagerAdapter(){
    }
    public MyPagerAdapter(Activity mActivity,List<View> mListViews){
        this.mActivity=mActivity;
        this.mListViews=mListViews;
    }
    public int getCount() {
        if (mListViews.size() == 1) {// 一张图片时不用流动
            return mListViews.size();
        }
        return Integer.MAX_VALUE;
    }
    /**
     返回List中的图片元素装载到控件中
     */
    public Object instantiateItem(View v, int i) {
        if (((ViewPager) v).getChildCount() == mListViews.size()) {
            ((ViewPager) v)
                    .removeView(mListViews.get(i % mListViews.size()));
        }
        ((ViewPager) v).addView(mListViews.get(i % mListViews.size()), 0);
        return mListViews.get(i % mListViews.size());
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }

    public void destroyItem(ViewGroup view, int i, Object object) {
        view.removeView(mListViews.get(i % mListViews.size()));
    }

}

