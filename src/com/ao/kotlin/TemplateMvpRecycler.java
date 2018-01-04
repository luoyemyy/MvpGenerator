package com.ao.kotlin;

public interface TemplateMvpRecycler {


    String mvp =
            "package %1$s\n\n" +

                    "import com.github.luoyemyy.framework.mvp.IMvp\n\n" +

                    "internal interface I%2$sMvp {\n" +
                    "   interface I%2$sModel : IMvp.IModel \n" +
                    "   interface I%2$sPresenter : IMvp.IPresenter {\n" +
                    "       %3$s" +
                    "   }\n" +
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
                    "   %3$s\n" +

                    "   init {\n" +
                    "//       setBusCallback(BusCode.MAIN)\n" +
                    "   }\n\n" +

                    "   %4$s\n" +

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
                    "   %3$s\n" +

                    "   init {\n" +
                    "//       setBusCallback(BusCode.MAIN)\n" +
                    "   }\n\n" +

                    "   %4$s\n" +

                    "   override fun onCreate(savedInstanceState: Bundle?) {\n\n" +
                    "   }\n" +

                    "}";


    String presenter_recycler =
                    "   override fun get%1$sRecyclerPresenter(adapter: %1$sRecyclerAdapter): %1$sRecyclerPresenter {\n" +
                    "       m%1$sRecyclerPresenter = %1$sRecyclerPresenter(this, mModel, mView, adapter)\n" +
                    "       return m%1$sRecyclerPresenter\n" +
                    "   }\n";

    String model =
            "package %1$s\n\n" +

                    "import android.content.Context\n\n" +

                    "internal class %2$sModelImpl(private val mContext: Context) : I%2$sMvp.I%2$sModel {\n\n" +

                    "//   private val mApiService: ApiService = ApiService(mContext)\n\n" +

                    "}";


    String recycler_presenter =

            "package %1$s\n\n" +

                    "import com.github.luoyemyy.framework.mvp.recycler.AbsRecyclerPresenter\n\n" +

                    "internal class %3$sRecyclerPresenter(private val mPresenter: I%2$sMvp.I%2$sPresenter, private val mModel: I%2$sMvp.I%2$sModel, private val mView: I%2$sMvp.I%2$sView, val adapter: %3$sRecyclerAdapter) : AbsRecyclerPresenter<%4$s>(mPresenter, adapter, adapter) {\n\n" +


                    "   init {\n" +
                    "       adapter.withPresenter(mPresenter, this)\n" +
                    "       //getDataSet().enableEmpty(false)\n" +
                    "       //getDataSet().enableMore(DataSet.MORE_NONE)\n" +
                    "   }\n" +
                    "}\n";

    String adapter =
            "package %1$s\n\n" +

                    "import android.content.Context\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout\n" +
                    "import android.support.v7.widget.RecyclerView\n" +
                    "import android.view.LayoutInflater\n" +
                    "import android.view.View\n" +
                    "import android.view.ViewGroup\n\n" +

                    "import com.github.luoyemyy.framework.mvp.recycler.AbsRecyclerAdapter\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.DataSet\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.VHEmptyData\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.VHEmptyError\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.VHMoreEnd\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.VHMoreError\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.VHMoreLoading\n" +
                    "import com.github.luoyemyy.framework.mvp.recycler.VHNull\n\n" +

                    "internal class %3$sRecyclerAdapter(context: Context, recyclerView: RecyclerView, swipeRefreshLayout: SwipeRefreshLayout?, private val mView: I%2$sMvp.I%2$sView) : AbsRecyclerAdapter(context, recyclerView, swipeRefreshLayout) {\n\n" +

                    "   private val mInflater: LayoutInflater = LayoutInflater.from(context)\n" +
                    "   private lateinit var mPresenter: I%2$sMvp.I%2$sPresenter\n" +
                    "   private lateinit var mRecyclerPresenter: %3$sRecyclerPresenter\n\n" +

                    "   fun withPresenter(presenter: I%2$sMvp.I%2$sPresenter, recyclerPresenter: %3$sRecyclerPresenter) {\n" +
                    "       mPresenter = presenter\n" +
                    "       mRecyclerPresenter = recyclerPresenter\n" +
                    "       initView()\n" +
                    "   }\n\n" +

                    "   override fun getRecyclerPresenter(): %3$sRecyclerPresenter = mRecyclerPresenter\n\n" +

                    "   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =\n" +
                    "        when (viewType) {\n" +
                    "          DataSet.Type.EMPTY -> VHEmptyData(mContext, parent)\n" +
                    "          DataSet.Type.EMPTY_ERROR -> VHEmptyError(mContext, parent, mRecyclerPresenter)\n" +
                    "          DataSet.Type.MORE_LOADING -> VHMoreLoading(mContext, parent)\n" +
                    "          DataSet.Type.MORE_END -> VHMoreEnd(mContext, parent)\n" +
                    "          DataSet.Type.MORE_ERROR -> VHMoreError(mContext, parent, mRecyclerPresenter)\n" +
                    "          DataSet.Type.CONTENT -> VH(mInflater.inflate(0, parent, false))\n" +
                    "          else -> VHNull(mContext)\n" +
                    "       }\n\n" +

                    "   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {\n" +
                    "       when (getItemViewType(position)) {\n" +
                    "           DataSet.Type.CONTENT -> {\n" +
                    "               val vh = holder\n" +
                    "           }\n" +
                    "       }\n" +
                    "   }\n\n" +

                    "   private inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) \n" +

                    "}";

    String view_activity =
            "package %1$s\n\n" +

                    "import android.os.Bundle\n\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout\n" +
                    "import android.support.v7.widget.RecyclerView\n\n" +

                    "import com.github.luoyemyy.framework.mvp.MvpActivity\n\n" +

                    "class %2$sActivity : MvpActivity(), I%2$sMvp.I%2$sView {\n\n" +

                    "   private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout\n" +
                    "   private lateinit var mRecyclerView: RecyclerView\n\n" +

                    "   private lateinit var mPresenter: I%2$sMvp.I%2$sPresenter\n" +
                    "   %3$s\n" +

                    "   override fun onCreate(savedInstanceState: Bundle?) {\n" +
                    "       super.onCreate(savedInstanceState)\n" +
                    "//        setContentView(R.layout.)\n" +
                    "       initViewAndPresenter()\n" +
                    "   }\n\n" +

                    "   override fun initViewAndPresenter() {\n" +
                    "       //mRecyclerView = findViewById(R.id.recyclerView)\n" +
                    "       //mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)\n\n" +

                    "       mPresenter = %2$sPresenterImpl(this, this)\n" +
                    "       %4$s\n\n" +
                    "   }\n" +
                    "}";

    String view_fragment =
            "package %1$s\n\n" +

                    "import android.os.Bundle\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout\n" +
                    "import android.support.v7.widget.RecyclerView\n" +
                    "import android.view.LayoutInflater\n" +
                    "import android.view.View\n" +
                    "import android.view.ViewGroup\n\n" +

                    "import com.github.luoyemyy.framework.mvp.MvpFragment\n\n" +

                    "class %2$sFragment : MvpFragment(), I%2$sMvp.I%2$sView {\n\n" +

                    "   private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout\n" +
                    "   private lateinit var mRecyclerView: RecyclerView\n\n" +

                    "   private lateinit var mPresenter: I%2$sMvp.I%2$sPresenter\n\n" +
                    "   %3$s\n" +

                    "   override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {\n" +
                    "       val view = inflater?.inflate(0, null)\n" +
                    "       return initViewAndPresenter(view!!)\n" +
                    "   }\n\n" +

                    "   override fun initViewAndPresenter(v: View): View {\n" +
                    "       //mRecyclerView = v.findViewById(R.id.recyclerView)\n" +
                    "       //mSwipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout)\n\n" +

                    "       mPresenter = %2$sPresenterImpl(this, this)\n" +
                    "       %4$s\n\n" +

                    "       return v\n" +
                    "   }\n" +
                    "}";


}
