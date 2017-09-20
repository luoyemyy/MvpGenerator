package com.ao.java;

interface MvpRecyclerTemplate {


    String MVP_TEMPLATE =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp_recycler.IRecycler;\n\n" +

                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel extends IRecycler.IModel {}\n" +
                    "   interface I%2$sPresenter extends IRecycler.IPresenter<Object> {}\n" +
                    "   interface I%2$sView extends IRecycler.IView {}\n" +
                    "}";


    String PRESENTER_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.app.Activity;\n" +
                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +

                    "import com.ao.framework.mvp_recycler.DataSet;\n" +
                    "import com.ao.framework.mvp_recycler.RecyclerPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends RecyclerPresenterImpl<Object> implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Activity mActivity;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(Activity activity,I%2$sMvp.I%2$sView view){\n" +
                    "       super(activity, new DataSet<Object>(true, DataSet.MORE_AFTER, view.getAdapter()), view);\n" +
                    "//       setViewRefresh(view);\n" +
                    "//       setViewMore(view);\n" +
                    "//       setViewSearch(view);\n" +
                    "       mContext = activity;\n" +
                    "       mActivity = activity;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
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