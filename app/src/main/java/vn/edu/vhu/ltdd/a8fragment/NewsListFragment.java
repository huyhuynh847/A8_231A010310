package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class NewsListFragment extends Fragment {

    public static final String REQUEST_KEY = "news_result";

    private TextView tvDaDoc;
    private LinearLayout boxTin;
    private String tinDaDoc = "";

    private A8ViewModel viewModel;

    public NewsListFragment() {
        super(R.layout.fragment_news_list);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // ViewModel dùng chung với Activity và các Fragment khác
        viewModel = new ViewModelProvider(requireActivity())
                .get(A8ViewModel.class);

        tvDaDoc = view.findViewById(R.id.tvDaDoc);
        boxTin = view.findViewById(R.id.boxTin);

        Button btnNews1 = view.findViewById(R.id.btnNews1);
        Button btnNews2 = view.findViewById(R.id.btnNews2);

        // Khôi phục trạng thái tin đã đọc
        tinDaDoc = viewModel.getTinDaDoc();
        capNhatTinDaDoc();

        // Nhận kết quả khi người dùng đánh dấu đã đọc
        getParentFragmentManager().setFragmentResultListener(
                REQUEST_KEY,
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    tinDaDoc = result.getString(
                            NewsDetailFragment.KEY_TIEU_DE, "");

                    viewModel.setTinDaDoc(tinDaDoc);
                    capNhatTinDaDoc();
                }
        );

        // Tin thứ nhất
        btnNews1.setOnClickListener(v ->
                moChiTiet(
                        "Thông báo học vụ VHU",
                        "Thông tin về lịch học, lịch thi và các thông báo học vụ của trường VHU."
                )
        );

        // Tin thứ hai
        btnNews2.setOnClickListener(v ->
                moChiTiet(
                        "Hoạt động sinh viên",
                        "Thông tin về các hoạt động, phong trào và sự kiện dành cho sinh viên."
                )
        );
    }

    // Mở chi tiết tùy theo bố cục điện thoại hoặc tablet
    private void moChiTiet(String title, String body) {

        NewsDetailFragment fragment =
                NewsDetailFragment.newInstance(title, body);

        if (requireActivity().findViewById(R.id.detailContainer) != null) {

            // Tablet: hiển thị chi tiết ở khung bên phải
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailContainer, fragment)
                    .setReorderingAllowed(true)
                    .commit();

        } else {

            // Điện thoại: thay khung danh sách bằng chi tiết
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack("chi_tiet")
                    .setReorderingAllowed(true)
                    .commit();
        }
    }

    private void capNhatTinDaDoc() {
        if (tvDaDoc == null) {
            return;
        }

        if (tinDaDoc == null || tinDaDoc.isEmpty()) {
            tvDaDoc.setText("Chưa đọc tin nào");
        } else {
            tvDaDoc.setText("Tin đã đọc: " + tinDaDoc);
        }

        if (boxTin != null) {
            boxTin.setVisibility(View.VISIBLE);
        }
    }
}
