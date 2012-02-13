package com.rolfbenz.lp.view;

import com.rolfbenz.lp.entity.Cutterteammitglied;
import com.rolfbenz.lp.entity.Cutterzuweisung;

import java.util.List;

/**
 * User: U987
 * Date: 04.04.11
 */
public class Cutterbuchungsmaske {

    private Cutterzuweisung cutterzuweisung;
    private List<Cutterteammitglied> cutterteammitgliedList;

    public Cutterzuweisung getCutterzuweisung() {
        return cutterzuweisung;
    }

    public void setCutterzuweisung(Cutterzuweisung cutterzuweisung) {
        this.cutterzuweisung = cutterzuweisung;
    }

    public List<Cutterteammitglied> getCutterteammitgliedList() {
        return cutterteammitgliedList;
    }

    public void setCutterteammitgliedList(List<Cutterteammitglied> cutterteammitgliedList) {
        this.cutterteammitgliedList = cutterteammitgliedList;
    }

}
