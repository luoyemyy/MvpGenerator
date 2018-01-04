package com.ao.kotlin;

public interface TemplateMvp {

    String mvp =
            "package %1$s\n\n" +

                    "import com.github.luoyemyy.framework.mvp.IMvp\n\n" +

                    "internal interface I%2$sMvp {\n" +
                    "   interface I%2$sModel : IMvp.IModel \n" +
                    "   interface I%2$sPresenter : IMvp.IPresenter \n" +
                    "   interface I%2$sView : IMvp.IView \n" +
                    "}";


    String presenter_activity =
            "package %1$s\n\n" +

                    "import android.content.Context\n" +
                    "import android.os.Bundle\n" +
                    "import android.support.v7.app.AppCompatActivity\n" +

                    "import com.github.luoyemyy.framework.mvp.MvpPresenterImpl\n\n" +

                    "internal class %2$sPresenterImpl(private val mActivity: AppCompatActivity, private val mView: I%2$sMvp.I%2$sView) : MvpPresenterImpl(mView), I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private val mContext: Context = mActivity.applicationContext\n" +
                    "   private val mModel: I%2$sMvp.I%2$sModel by lazy { %2$sModelImpl(mContext) }\n\n" +

                    "   init {\n" +
                    "//       setBusCallback(BusCode.MAIN)\n" +
                    "   }\n\n" +

                    "   override fun onCreate(savedInstanceState: Bundle?) {\n\n" +
                    "   }\n" +

                    "}";

    String presenter_fragment =
            "package %1$s\n\n" +

                    "import android.content.Context\n" +
                    "import android.os.Bundle\n" +
                    "import android.support.v4.app.Fragment\n" +

                    "import com.github.luoyemyy.framework.mvp.MvpPresenterImpl\n\n" +

                    "internal class %2$sPresenterImpl(private val mFragment: Fragment, private val mView: I%2$sMvp.I%2$sView) : MvpPresenterImpl(mView), I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private val mContext: Context = mFragment.context.applicationContext\n" +
                    "   private val mModel: I%2$sMvp.I%2$sModel by lazy { %2$sModelImpl(mContext) }\n\n" +

                    "   init {\n" +
                    "//       setBusCallback(BusCode.MAIN)\n" +
                    "   }\n\n" +

                    "   override fun onCreate(savedInstanceState: Bundle?) {\n\n" +
                    "   }\n" +

                    "}";

    String model =
            "package %1$s\n\n" +

                    "import android.content.Context\n\n" +

                    "internal class %2$sModelImpl(private val mContext: Context) : I%2$sMvp.I%2$sModel {\n\n" +

                    "//   private val mApiService: ApiService = ApiService(mContext)\n\n" +

                    "}";

    String view_activity =
            "package %1$s\n\n" +

                    "import android.os.Bundle\n\n" +
                    "import com.github.luoyemyy.framework.mvp.MvpActivity\n\n" +

                    "class %2$sActivity : MvpActivity(), I%2$sMvp.I%2$sView {\n\n" +

                    "   private lateinit var mPresenter: I%2$sMvp.I%2$sPresenter\n\n" +

                    "   override fun onCreate(savedInstanceState: Bundle?) {\n" +
                    "       super.onCreate(savedInstanceState)\n" +
                    "//        setContentView(R.layout.)\n" +
                    "       initViewAndPresenter()\n" +
                    "   }\n\n" +

                    "   override fun initViewAndPresenter() {\n" +
                    "       mPresenter = %2$sPresenterImpl(this, this)\n" +
                    "   }\n" +
                    "}";

    String view_fragment =
            "package %1$s\n\n" +

                    "import android.os.Bundle\n" +
                    "import android.view.LayoutInflater\n" +
                    "import android.view.View\n" +
                    "import android.view.ViewGroup\n\n" +

                    "import com.github.luoyemyy.framework.mvp.MvpFragment\n\n" +

                    "class %2$sFragment : MvpFragment(), I%2$sMvp.I%2$sView {\n\n" +

                    "   private lateinit var mPresenter: I%2$sMvp.I%2$sPresenter\n\n" +

                    "   override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {\n" +
                    "       val view = inflater?.inflate(0, null)\n" +
                    "       return initViewAndPresenter(view!!)\n" +
                    "   }\n\n" +

                    "   override fun initViewAndPresenter(v: View): View {\n" +
                    "       mPresenter = %2$sPresenterImpl(this, this)\n" +
                    "       return v\n" +
                    "   }\n" +
                    "}";
}
