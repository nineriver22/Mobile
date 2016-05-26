package uilts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.quantong.mobilefix.R;

/**
 * Created by Constantine on 2016/5/5.
 */
public class SlidingMenu extends HorizontalScrollView {

    private final String TAG = "SlidingMenu";
    public static boolean isIntercept = true;

    private static boolean isOpen = false;
    private int mScreenWidth;
    private int mMenuRightPadding;
    private int mMenuWidth;
    private float mSlidingWidth;

    private ViewGroup mMenu;
    private ViewGroup mContent;

    public SlidingMenu(Context context) {
        this(context, null, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = ScreenUtils.getScreenWidth(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 50f, getResources().getDisplayMetrics()));// 默认为50DP
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LinearLayout wrapper = (LinearLayout) getChildAt(0);
        mMenu = (ViewGroup) wrapper.getChildAt(0);
        mContent = (ViewGroup) wrapper.getChildAt(1);

        mMenuWidth = mScreenWidth - mMenuRightPadding;
        mSlidingWidth = mMenuWidth - mMenuRightPadding / 2;
        mMenu.getLayoutParams().width = mMenuWidth;
        mContent.getLayoutParams().width = mScreenWidth;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.scrollTo(mMenuWidth, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (!isOpen && scrollX < mSlidingWidth) {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                } else {
                    if (scrollX > mMenuRightPadding / 2) {
                        this.smoothScrollTo(mMenuWidth, 0);
                        isOpen = false;
                    } else {
                        this.smoothScrollTo(0, 0);
                        isOpen = true;
                    }
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        mMenu.setScaleX(leftScale);
        mMenu.setScaleY(leftScale);
        mMenu.setAlpha(0.6f + 0.4f * (1 - scale));
        mMenu.setTranslationX(mMenuWidth * scale * 0.7f);

        mContent.setPivotX(0);
        mContent.setPivotY(mContent.getHeight() / 2);
        mContent.setScaleX(rightScale);
        mContent.setScaleY(rightScale);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isIntercept) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void slideMenu() {
        if (!isOpen) {
            this.smoothScrollTo(0, 0);
            isOpen = true;
        } else {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

}