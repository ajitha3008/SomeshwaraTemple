package braingalore.someshwaratemple.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import braingalore.someshwaratemple.MainActivity;
import braingalore.someshwaratemple.R;
import braingalore.someshwaratemple.banner.SlidingImageAdapter;

/**
 * Created by ajitha3008 on 11/11/17.
 */

public class HomeFragment extends Fragment {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.dhyana_mandira, R.drawable.ym1, R.drawable.ym2, R.drawable.ym3};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    String about = "<html><body style=\"text-align:justify\">The Someshware temple at Madivala is one of Bangalore's oldest, dating back to the Chola period. There are a number of Tamil and Grantha inscriptions on the outer walls of the temple. The oldest of these inscriptions dates to 1247 AD talks about a land grants \"below the big tank of Vengalur\" by a Veppur (modern Begur) resident.</body></Html>";
    private WebView aboutView;
    String htmlText = " %s ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.home_fragment, null);
        for (int i = 0; i < IMAGES.length; i++) {
            ImagesArray.add(IMAGES[i]);
        }
        ((MainActivity)getActivity()).disableBackInSupportActionBar();
        aboutView = (WebView) v.findViewById(R.id.webView_about);
        aboutView.loadData(String.format(htmlText, about), "text/html", "utf-8");
        mPager = (ViewPager) v.findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(getActivity(), ImagesArray));
        CirclePageIndicator indicator = (CirclePageIndicator) v.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

        return v;
    }

}
