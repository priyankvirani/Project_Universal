package com.project.universal.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.project.universal.R;
import com.project.universal.adapter.NavigationDrawerMenuAdapter;
import com.project.universal.fragment.BaseFragment;
import com.project.universal.fragment.IUpdateFragment;
import com.project.universal.model.NavigationDrawerMenu;
import com.project.universal.utlis.IConstants.IDrawerMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NavigationDrawerActivity extends BaseActivity {

    private static final String TAG = NavigationDrawerActivity.class.getSimpleName();

    @BindView(R.id.txtFragmentTitle)
    public TextView txtFragmentTitle;
    @BindView(R.id.txtCartCount)
    public TextView txtCartCount;

    /**
     * Container for all fragments
     */
    Fragment content;

    /**
     * For tool bar and left drawer
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.recyclerListNavigationDrawer)
    RecyclerView recyclerListNavigationDrawer;
    /**
     * Basically to change title in tool bar.
     * <p/>
     * First get the current fragment and then calling its onStart() method to
     * set title.
     */
    FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {

        @Override
        public void onBackStackChanged() {
            // TODO Auto-generated method stub
            // Update your UI here.
            Log.i(TAG, "OnBackStackChangedListener");
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager
                    .findFragmentById(R.id.contentFrame);
            if (currentFragment != null
                    && (currentFragment instanceof IUpdateFragment)) {
                Log.e(TAG, currentFragment.getClass().getSimpleName());
                ((BaseFragment) currentFragment).updateFragment();
            }
        }
    };
    /**
     * Menu drawer item click listener to set respective fragment
     */
    NavigationDrawerMenuAdapter.OnItemClickListener leftDrawerListItemClickListener = new NavigationDrawerMenuAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (view != null && view.getTag() != null) {
                Log.d(TAG, "menuName = " + view.getTag());

                int menuTagId = (int) view.getTag();
                Fragment newContent = null;
                switch (menuTagId) {

                    case IDrawerMenu.HOME_ID:
                        toggleLeftDrawer();
                        break;
                    case IDrawerMenu.UNDER_DEVLOPMENT_ID:
                        break;

                }
                if (newContent != null) {
                    switchContent(newContent, true);
                }
            }
        }
    };


    private ActionBarDrawerToggle drawerToggle;
    private ArrayList<NavigationDrawerMenu> navigationDrawerMenuArrayList;
    private NavigationDrawerMenuAdapter drawerMenuAdapter;
    /**
     * For double back exit functionality
     */
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }

        initDrawer();

        onNewIntent(getIntent());

        if (content == null) {
            Log.i(TAG, "content is null");
            //switchContent(new MyOrderListFragment(), true);
        }
        getSupportFragmentManager().addOnBackStackChangedListener(
                onBackStackChangedListener);


    }

    private void setMenuAdapter() {
        navigationDrawerMenuArrayList = new ArrayList<>();


        navigationDrawerMenuArrayList.add(new NavigationDrawerMenu(IDrawerMenu.HOME,
                IDrawerMenu.HOME_ID, R.drawable.logo_home));

        navigationDrawerMenuArrayList.add(new NavigationDrawerMenu(IDrawerMenu.UNDER_DEVELOPMENT,
                IDrawerMenu.UNDER_DEVLOPMENT_ID, R.drawable.logo_home));

    }

    private void initDrawer() {
        setMenuAdapter();
        drawerMenuAdapter = new NavigationDrawerMenuAdapter(navigationDrawerMenuArrayList);
        drawerMenuAdapter.setOnItemClickListener(leftDrawerListItemClickListener);
        recyclerListNavigationDrawer.setLayoutManager(new LinearLayoutManager(NavigationDrawerActivity.this));
        recyclerListNavigationDrawer.setAdapter(drawerMenuAdapter);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.e(TAG, "onDrawerClosed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.e(TAG, "onDrawerOpened");
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle
                .setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "setToolbarNavigationClickListener");
                        toggleLeftDrawer();
                    }
                });

        drawerLayout.setDrawerListener(drawerToggle);
    }

    @OnClick(R.id.imgToggleDrawer)
    public void toggleLeftDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onBackPressed() {

        Log.i(TAG, "Back");

        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            int backStackEntryCount = manager.getBackStackEntryCount();
            Log.i(TAG, " getSupportFragmentManager backStackEntryCount = "
                    + backStackEntryCount);
            // hide left drawer
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                hideDrawersAndShowContent();
            }
            // logic for two time back press exit app
            else if (backStackEntryCount == 1) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    finish();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                if (getApplicationContext() == null) {
                    return;
                } else {
                    Toast.makeText(this, "Please click BACK again to exit",
                            Toast.LENGTH_SHORT).show();
                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();
            }
        }

    }

    /**
     * Set title
     */
    public void setToolbarTitle(String toolbarTitle) {
        txtFragmentTitle.setText(toolbarTitle);
    }

    /*
     * For switching fragments
     */
    public void switchContent(Fragment fragment, boolean emptyStack) {
        FragmentManager manager = getSupportFragmentManager();
//        To hide keyboard on fragment change
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        content = fragment;
        String fragmentTag = fragment.getClass().getSimpleName();

        // if (backStateName.compareTo(CategoryListFragment.class.getSimpleName()
        // .toString()) == 0
        // || backStateName.compareTo(VideoListFragment.class.getSimpleName()
        // .toString()) == 0
        // || backStateName.compareTo(SettingsFragment.class.getSimpleName()
        // .toString()) == 0) {
        if (emptyStack) {
            // Remove all inner fragments of previous section
            boolean fragmentPopped = manager.popBackStackImmediate(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            Log.i(TAG, "fragmentPopped = " + fragmentPopped);
        }

        Log.i(TAG, "fragmentTag = " + fragmentTag);
        FragmentTransaction ft = manager.beginTransaction();
//        ft.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter,
//                R.anim.exit);
        Fragment tmpMyFragment = manager.findFragmentById(R.id.contentFrame);
        if (tmpMyFragment != null) {
            ft.hide(tmpMyFragment);
        }
        ft.add(R.id.contentFrame, fragment, fragmentTag);
        ft.addToBackStack(fragmentTag);
        ft.commit();

        hideDrawersAndShowContent();
    }

    private void hideDrawersAndShowContent() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
