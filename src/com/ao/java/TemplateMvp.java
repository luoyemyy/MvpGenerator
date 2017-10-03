package com.ao.java;

public interface TemplateMvp {

    String mvp =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp.IMvp;\n\n" +

                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel extends IMvp.IModel {}\n" +
                    "   interface I%2$sPresenter extends IMvp.IPresenter {}\n" +
                    "   interface I%2$sView extends IMvp.IView {}\n" +
                    "}";


    String presenter_activity =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v7.app.AppCompatActivity;\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private AppCompatActivity mActivity;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(AppCompatActivity activity,I%2$sMvp.I%2$sView view){\n" +
                    "       super(view);\n" +
                    "       mContext = activity;\n" +
                    "       mActivity = activity;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n\n" +
                    "   }\n" +

                    "}";

    String presenter_fragment =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.app.Fragment;\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Fragment mFragment;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(Fragment fragment,I%2$sMvp.I%2$sView view){\n" +
                    "       super(view);\n" +
                    "       mContext = fragment.getContext();\n" +
                    "       mFragment = fragment;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n\n" +
                    "   }\n" +

                    "}";

    String model =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n\n" +

                    "class %2$sModelImpl implements I%2$sMvp.I%2$sModel {\n\n" +

                    "   private Context mContext;\n" +
                    "//   private ApiService mApiService;\n\n" +

                    "   %2$sModelImpl(Context context){\n" +
                    "       mContext = context;\n" +
                    "//       mApiService = new ApiService(context);\n" +
                    "   }\n" +

                    "}";

    String view_activity =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp.MvpActivity;\n\n" +

                    "public class %2$sActivity extends MvpActivity implements I%2$sMvp.I%2$sView {\n\n" +

                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n\n" +

                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                    "       super.onCreate(savedInstanceState);\n" +
                    "//        setContentView(R.layout.);\n" +
                    "       initViewAndPresenter();\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected void initViewAndPresenter() {\n" +
                    "       mPresenter = new %2$sPresenterImpl(this, this);\n" +
                    "   }\n" +
                    "}";

    String view_fragment =
            "package %1$s;\n\n" +

                    "import android.view.View;\n\n" +

                    "import com.ao.framework.mvp.MvpFragment;\n\n" +

                    "public class %2$sFragment extends MvpFragment implements I%2$sMvp.I%2$sView {\n\n" +

                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n\n" +

                    "   @Nullable\n" +
                    "   @Override\n" +
                    "   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
                    "       View view = inflater.inflate(0, null);\n" +
                    "       return initViewAndPresenter(view);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected View initViewAndPresenter(View v) {\n" +
                    "       mPresenter = new %2$sPresenterImpl(this, this);\n" +
                    "       return v;\n" +
                    "   }\n" +
                    "}";
}
