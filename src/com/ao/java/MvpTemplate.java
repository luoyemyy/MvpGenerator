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


    String PRESENTER_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.app.Fragment;\n" +
                    "import android.support.v7.app.AppCompatActivity;\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private %3$s %5$s;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +

                    "   %2$sPresenterImpl(%3$s %4$s,I%2$sMvp.I%2$sView view){\n" +
                    "       super(view);\n" +
                    "       mContext = %4$s%6$s;\n" +
                    "       %5$s = %4$s;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n\n" +
                    "   }\n" +

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