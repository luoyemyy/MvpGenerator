package com.ao.java;

interface MvpTemplate {

    String MVP_TEMPLATE =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp.IMvp;\n\n" +

                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel extends IMvp.IModel {}\n" +
                    "   interface I%2$sPresenter extends IMvp.IPresenter {}\n" +
                    "   interface I%2$sView extends IMvp.IView {}\n" +
                    "}";

    String MVP_RECYCLER_TEMPLATE =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp_recycler.IRecycler;\n\n" +

                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel extends IRecycler.IModel {}\n" +
                    "   interface I%2$sPresenter extends IRecycler.IPresenter<Object> {}\n" +
                    "   interface I%2$sView extends IRecycler.IView {}\n" +
                    "}";

    String PRESENTER_FRAGMENT_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.app.Fragment;\n\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Fragment mFragment;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(%2$sFragment fragment){\n" +
                    "       super(fragment,fragment);\n" +
                    "       mContext = fragment.getContext();\n" +
                    "       mFragment = fragment;\n" +
                    "       mView = fragment;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "   }\n\n" +
                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n\n" +
                    "   }\n" +

                    "}";

    String PRESENTER_ACTIVITY_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.app.Activity;\n" +
                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Activity mActivity;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +
                    
                    "   %2$sPresenterImpl(%2$sActivity activity){\n" +
                    "       super(activity,activity);\n" +
                    "       mContext = activity;\n" +
                    "       mActivity = activity;\n" +
                    "       mView = activity;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n\n" +
                    "   }\n" +

                    "}";

    String PRESENTER_RECYCLER_FRAGMENT_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.app.Fragment;\n\n" +

                    "import com.ao.framework.mvp_recycler.DataSet;\n"+
                    "import com.ao.framework.mvp_recycler.RecyclerPresenterImpl;\n\n"+

                    "class %2$sPresenterImpl extends RecyclerPresenterImpl<Object> implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Fragment mFragment;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(%2$sFragment fragment){\n" +
                    "       super(fragment, new DataSet<>(true, 1, fragment.getAdapter()), fragment);\n" +
                    "       mContext = fragment.getContext();\n" +
                    "       mFragment = fragment;\n" +
                    "       mView = fragment;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "   }\n\n" +
                    "}";

    String PRESENTER__RECYCLER_ACTIVITY_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.app.Activity;\n"+
                    "import android.content.Context;\n"+
                    "import android.os.Bundle;\n"+
                    "import android.support.annotation.Nullable;\n\n"+

                    "import com.ao.framework.mvp_recycler.DataSet;\n"+
                    "import com.ao.framework.mvp_recycler.RecyclerPresenterImpl;\n\n"+

                    "class %2$sPresenterImpl extends RecyclerPresenterImpl<Object> implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Activity mActivity;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(%2$sActivity activity){\n" +
                    "       super(activity, new DataSet<>(true, 1, activity.getAdapter()), activity);\n" +
                    "       mContext = activity;\n" +
                    "       mActivity = activity;\n" +
                    "       mView = activity;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "   }\n\n" +

                    "}";

    String MODEL_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n\n" +

                    "class %2$sModelImpl implements I%2$sMvp.I%2$sModel {\n\n" +

                    "   private Context mContext;\n" +
                    "//   private ApiService mApiService;\n\n" +

                    "   %2$sModelImpl(Context context){\n" +
                    "       mContext = context;\n" +
                    "//       mApiService = new ApiService(context);\n" +
                    "   }\n\n" +

                    "}";


}