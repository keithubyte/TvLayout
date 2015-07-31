###   关于屏幕适配的一个解决方案

#### 原理与实现代码

首先来理清一下常见视图的继承关系：

![tvlayout](http://7jpokz.com1.z0.glb.clouddn.com/TvLayout.png)

结合上图来说明一下：

- 在常见的几种容器布局中，不管是在 `xml` 布局文件中添加子视图，还是通过代码动态添加子视图，最终都是通过 `addView(View child, int index, ViewGroup.LayoutParams params)` 这个方法来添加到容器中的；
- 与尺寸相关的属性，已经在图中标识出来，此处略过。

根据上述的事实，就可以考虑从 `addView()` 方法作为入手点，在子视图将要添加到父视图之前，先对子视图的尺寸相关属性进行调整。这个处理的过程，具体代码如下：

```java
/**
 * Resize the size-relative attributions
 *
 * @param child
 * @param params
 */
public static void resize(View child, ViewGroup.LayoutParams params) {
    if (child != null && params != null) {
        // width and height
        params.width = params.width > 0 ? (int) (params.width * wRadio) : params.width;
        params.height = params.height > 0 ? (int) (params.height * hRadio) : params.height;

        // margin
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) params;
            p.leftMargin = (int) (p.leftMargin * wRadio);
            p.topMargin = (int) (p.topMargin * hRadio);
            p.rightMargin = (int) (p.rightMargin * wRadio);
            p.bottomMargin = (int) (p.bottomMargin * hRadio);
            p.setMarginStart((int) (p.getMarginStart() * aRadio));
            p.setMarginEnd((int) (p.getMarginEnd() * aRadio));
        }

        // x & y
        if (params instanceof AbsoluteLayout.LayoutParams) {
            AbsoluteLayout.LayoutParams p = (AbsoluteLayout.LayoutParams) params;
            p.x = (int) (p.x * wRadio);
            p.y = (int) (p.y * hRadio);
        }

        // padding
        int paddingLeft = (int) (child.getPaddingLeft() * wRadio);
        int paddingTop = (int) (child.getPaddingTop() * hRadio);
        int paddingRight = (int) (child.getPaddingRight() * wRadio);
        int paddingBottom = (int) (child.getPaddingBottom() * hRadio);
        child.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        // font size and others
        if (child instanceof TextView) {
            TextView tv = (TextView) child;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv.getTextSize() * aRadio / sRadio);
            tv.setCompoundDrawablePadding((int) (tv.getCompoundDrawablePadding() * aRadio));
            tv.setMaxWidth((int) (tv.getMaxWidth() * wRadio));
            tv.setMaxHeight((int) (tv.getMaxHeight() * hRadio));
            tv.setMinWidth((int) (tv.getMinWidth() * wRadio));
            tv.setMinHeight((int) (tv.getMinHeight() * hRadio));
        }
    }
}
```

至于 `wRadio` 和 `hRadio`，分辨是水平比率和垂直比率，相关的获取代码如下：

```java
public class LayoutRadio {

    private static final String TAG = "LayoutRadio";

    public static int STANDARD_WIDTH = 1280;
    public static int STANDARD_HEIGHT = 720;

    public static int REAL_WIDTH = 0;
    public static int REAL_HEIGHT = 0;

    public static float RADIO_WIDTH = 1.0f;
    public static float RADIO_HEIGHT = 1.0f;
    public static float RADIO_AVERAGE = 1.0f;

    /**
     * 初始化比率，标准宽和高采用的是 1280 * 720
     * @param activity
     */
    public static void initRadio(Activity activity) {
        initRadio(activity, STANDARD_WIDTH, STANDARD_HEIGHT);
    }

    /**
     * 初始化比率，根据传入的值来设置标准的宽和高，传入的宽和高
     * 必须大于 0，否则会抛出 IllegalArgumentException 异常
     * @param activity
     * @param standardWidth
     * @param standardHeight
     */
    public static void initRadio(Activity activity, int standardWidth, int standardHeight) {
        if (standardWidth > 0 && standardHeight > 0) {
            STANDARD_WIDTH = standardWidth;
            STANDARD_HEIGHT = standardHeight;
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            REAL_WIDTH = point.x;
            REAL_HEIGHT = point.y;
            RADIO_WIDTH = REAL_WIDTH / (float) STANDARD_WIDTH;
            RADIO_HEIGHT = REAL_HEIGHT / (float) STANDARD_HEIGHT;
            RADIO_AVERAGE = (RADIO_WIDTH + RADIO_HEIGHT) / 2;
            Log.e(TAG, "REAL_WIDTH = " + REAL_WIDTH
                    + ", REAL_HEIGHT = " + REAL_HEIGHT
                    + ", RADIO_WIDTH = " + RADIO_WIDTH
                    + ", RADIO_HEIGHT = " + RADIO_HEIGHT
                    + ", RADIO_AVERAGE = " + RADIO_AVERAGE);
        } else {
            throw new IllegalArgumentException("Both standard width and height should be positive.");
        }
    }

}
```

最后，为了实现在执行 `addView()`方法前执行调节尺寸的代码，需要自定义父视图。以下以继承 `RelativeLayout` 为例子：

```java
public class TvRelativeLayout extends RelativeLayout {

    public TvRelativeLayout(Context context) {
        super(context);
    }

    public TvRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutResizer.resize(child, params); // resize the attributions before adding view
        super.addView(child, index, params);
    }
}
```

这样，不管是在 `xml` 还是代码动态添加控件，只要使用这个容器来作为父视图，就可以在添加前对子视图进行尺寸调整。

#### 方案评价

##### 优点
- 只要使用自定义的父视图作为容器，对于代码层来说，相关的代码（`addView` 或者 `inflate`）不需要增加特殊的逻辑处理；
- 不需要在布局完成后再去遍历所有的子视图来调整尺寸，最低限度地降低因调整尺寸而带来的额外开销。

#####缺点
- 对于库的开发者：理论上需要覆写所有的父视图，比较繁琐；
- 对于库的开发者：调整尺寸的时候，需要覆盖所有和尺寸相关的属性（上述的代码尚不知道是否已经覆盖完全）；
- 在 `xml` 中布局时，需要使用自定义的父视图，因而开发者必须知道有对应的自定义视图存在，并引入到 `xml` 中，因而，比起直接使用系统自带的布局，要更麻烦些；
- 布局时需要使用 `px` 作为单位来布局。

#### 未来目标

希望能在使用系统自带的视图容器的前提下，只在应用启动的时候，在固定的某一个/几个地方执行某种逻辑，即可达成适配的目标。但是，以我所了解到的信息来看，要实现这样的目标，貌似不太可行。
