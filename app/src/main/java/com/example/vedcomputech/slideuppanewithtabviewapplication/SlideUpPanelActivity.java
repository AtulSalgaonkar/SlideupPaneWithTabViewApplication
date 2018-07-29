package com.example.vedcomputech.slideuppanewithtabviewapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.vedcomputech.slideuppanewithtabviewapplication.Adapter.FragmentOneRvAdapter;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Adapter.FragmentTwoRvAdapter;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Adapter.ViewPagerAdapter;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Fragment.FragmentOne;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Fragment.FragmentTwo;
import com.example.vedcomputech.slideuppanewithtabviewapplication.HelperClass.HelperMethods;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class SlideUpPanelActivity extends FragmentActivity implements FragmentOneRvAdapter.OnLocationClickOne, FragmentTwoRvAdapter.OnLocationClickTwo {

    private Context mContext;
    private boolean isActive = false;
    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    private AppCompatTextView mDataOneAcTv, mDataTwoAcTv;
    private ImageView mUpDownIv;
    private LinearLayout mRootView;
    private static final String TAG = "SlideUpKey";
    private TranslateAnimation mAnimation;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CoordinatorLayout mPanelRootView;
    private String tabTitles[] = new String[]{"One", "Two"};
    private FragmentOne mFragmentOne;
    private FragmentTwo mFragmentTwo;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideup_panel);
        mContext = SlideUpPanelActivity.this;

        initView();

        mUpDownIv.setOnClickListener(v -> {
            setStateOfSlidingUpPannel();
        });

        panelListener();

    }

    private void setComponentOfDown() {
        Drawable down = getDrawableView(R.drawable.ic_keyboard_arrow_up);
        mUpDownIv.setImageDrawable(down);
        //((Animatable) mUpDownIv.getDrawable()).start();
        //mIncludeLayout.setVisibility(View.GONE);
    }

    private void setComponentOfUp() {
        Drawable up = getDrawableView(R.drawable.ic_keyboard_arrow_down);
        mUpDownIv.setImageDrawable(up);
        //((Animatable) mUpDownIv.getDrawable()).start();
        //mIncludeLayout.setVisibility(View.VISIBLE);
    }

    private Drawable getDrawableView(int val) {
        return getResources().getDrawable(val);
    }

    private void initView() {
        mUpDownIv = findViewById(R.id.up_down_iv);
        mSlidingUpPanelLayout = findViewById(R.id.sliding_layout);
        mRootView = findViewById(R.id.root_view);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mPanelRootView = findViewById(R.id.panel_root_view);
        mDataOneAcTv = findViewById(R.id.data_oen_ac_tv);
        mDataTwoAcTv = findViewById(R.id.data_two_ac_tv);
        mTabLayout.setupWithViewPager(mViewPager);

        initFragment();
        mViewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), tabTitles);
        mViewPagerAdapter.addFragments(mFragmentOne);
        mViewPagerAdapter.addFragments(mFragmentTwo);
        mViewPager.setAdapter(mViewPagerAdapter);

        /*mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        View view1 = LayoutInflater.from(mContext).inflate(R.layout.fragment_one_layout, null);
                        RecyclerView recyclerView1 = view1.findViewById(R.id.f_one_recycler_view);
                        mSlidingUpPanelLayout.setScrollableView(recyclerView1);
                        break;
                    case 1:
                        View view2 = LayoutInflater.from(mContext).inflate(R.layout.fragment_two_layout, null);
                        RecyclerView recyclerView2 = view2.findViewById(R.id.f_two_recycler_view);
                        mSlidingUpPanelLayout.setScrollableView(recyclerView2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }

    private void initFragment() {
        mFragmentOne = new FragmentOne();

        /*try {
            Bundle bundle = new Bundle();
            bundle.putInt(OPERATION_ID_KEY, mOperationId);
            bundle.putString(EMPLOYEE_MAP_ID_KEY, mEmployeeMapId);
            bundle.putString(EMPLOYEE_ID_KEY, mEmployeeId);
            mEmployeeDashboardFragment.setArguments(bundle);
        } catch (Exception e) {
            HelperMethods.showLogError("Error sending projectId to EmployeeDashboardFragment:\n" + e.getMessage());
        }*/

        mFragmentTwo = new FragmentTwo();

        /*try {
            Bundle bundle = new Bundle();
            bundle.putInt(OPERATION_ID_KEY, mOperationId);
            bundle.putString(EMPLOYEE_MAP_ID_KEY, mEmployeeMapId);
            bundle.putString(EMPLOYEE_ID_KEY, mEmployeeId);
            mEmployeeActivityFragment.setArguments(bundle);
        } catch (Exception e) {
            HelperMethods.showLogError("Error sending projectId to EmployeeDashboardActivityFragment:\n" + e.getMessage());
        }*/
    }

    private void setStateOfSlidingUpPannel() {
        if (isActive) {
            mSlidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            mSlidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }

    public void panelListener() {

        mSlidingUpPanelLayout.getChildAt(1).setOnClickListener(null);
        //set a Details on a sliding ImageView at Preview
        mSlidingUpPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            // During the transition of expand and collapse onPanelSlide function will be called.

            @Override
            public void onPanelSlide(View panel, float slideOffset) {

                //change the image icon here if up
                isActive = true;

                setComponentOfUp();
                HelperMethods.showLogData("Scrolling offset: " + slideOffset);
                //Re-fetch recyclerview data here
                //Log.e(TAG, "onPanelSlide, offset " + slideOffset);
            }

            // This method will be call after slide up layout
            @Override
            public void onPanelExpanded(View panel) {

            }

            // This method will be call after slide down layout.
            @Override
            public void onPanelCollapsed(View panel) {
                isActive = false;

                setComponentOfDown();
                //change the image icon here if down
                Log.e(TAG, "onPanelCollapsed");


            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.e(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.e(TAG, "onPanelHidden");
            }
        });
    }

    @Override
    public void onLocationClickListener(String name) {
        //here one
        isActive = true;
        setStateOfSlidingUpPannel();
        mDataOneAcTv.setText(name);
    }

    @Override
    public void onLocationClickListenerTwo(String name) {
        //here two
        isActive = true;
        setStateOfSlidingUpPannel();
        mDataTwoAcTv.setText(name);
    }
}
