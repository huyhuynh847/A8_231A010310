
package vn.edu.vhu.ltdd.a8fragment;

import androidx.lifecycle.ViewModel;

public class A8ViewModel extends ViewModel {

    private String tinDaDoc = "";
    private int selectedTab = R.id.tab_home;

    public String getTinDaDoc() {
        return tinDaDoc;
    }

    public void setTinDaDoc(String tinDaDoc) {
        this.tinDaDoc = tinDaDoc;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }
}
