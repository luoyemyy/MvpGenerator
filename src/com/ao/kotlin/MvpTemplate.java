package com.ao.kotlin;

interface MvpTemplate {

    String MVP_TEMPLATE =
                    "package %1$s\n\n" +
                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel : IMvp.IModel {}\n" +
                    "   interface I%2$sPresenter : IMvp.IPresenter {}\n" +
                    "   interface I%2$sView : IMvp.IView {}\n" +
                    "}";

    String PRESENTER_TEMPLATE =
                    "package %1$s\n\n" +
                    "import android.content.Context;\n\n" +

                    "class %2$sPresenterImpl(private var mContext: Context?, private var mView: I%2$sMvp.I%2$sView?) : MvpPresenterImpl<Any>(mView) , I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private var mModel: I%2$sMvp.I%2$sModel? = if (mContext != null) %2$sModelImpl(mContext!!) else null\n\n" +

                    "   override fun onDestroy() {\n" +
                    "       mView = null\n" +
                    "       mContext = null\n" +
                    "   }\n" +

                    "}";

    String MODEL_TEMPLATE =
                    "package %1$s\n\n" +

                    "import android.content.Context\n\n" +

                    "class %2$sModelImpl(private var mContext: Context) : I%2$sMvp.I%2$sModel {\n\n" +

                    "   private val mApiService = ApiService(mContext)\n\n"+

                    "}";


}