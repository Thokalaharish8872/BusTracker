package com.example.bustracker;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DirectionsFragment extends Fragment {
    ArrayList<data> arrNames = new ArrayList<>();
    FloatingActionButton refresh;
    ArrayList<data> array = new ArrayList<>();
    RecyclerView recyclerView;
    TextInputEditText from,to;
    private BottomSheetBehavior bottomSheetBehavior;

    public DirectionsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_directions, container, false);

        from = v.findViewById(R.id.fromloc);
        to = v.findViewById(R.id.toloc);
        AppCompatButton btnGo = v.findViewById(R.id.btnGo);
        EditText busNum = v.findViewById(R.id.BusNumber);
        ImageView btnSwap = v.findViewById(R.id.btnSwap);
        recyclerView = v.findViewById(R.id.recyclerView);
        TextView notFound = v.findViewById(R.id.notfound);
        ImageView dragBtn = v.findViewById(R.id.dragbtn);

        reverse(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        array.add(new data("Uppal","01","TS19A6514","8:25","9:20","08","30","17.40266368465565","78.55544544478455","17.43658129370765", "78.71591244569122","{_fiBoym~MUOUA{BVUaECCmBD[DQ_A{ArAg@m@m@[Ha@nAcHh@qDp@aEf@uA`@kAXk@d@i@\\WTOh@]n@WpAo@v@e@l@]f@S~@IhAG@{DAsCDkFE{B@}GC}E@wCCuHC}EC_CC}@Cc@MsA_@iCw@aFKaAEqABa@|A}Kv@kGf@oCFy@?sAEw@Oy@KWW_@m@m@kBcBu@u@s@_A{@kAw@qAoAqBU_@w@iB_@cA_@oAs@mDa@gC_A}E{@_E_@kBeAqE}AcHsB}I{AiHqAcHk@cCiBqJ]yAm@{B{@uCeAeDiA}D}AcG{@eDaA}Da@sA_DkKa@{AU{AEe@A_ARyCRmCDoBKqA]iB{AsFUgAmAoGkAgG_@kBi@}AgAiC{AyCyGmMo@uASi@Ke@UuAY{CWiCQsB_@aFKmDG_DSsE_AaNS}Aa@iBk@{AoAwByI{NgCkE}BuDqA_Cs@gBw@aCeCkHqBeHW{@k@{AcBiDaDkG{@aB}CwFkDgGwB_D_DgEuBgCuBuB{@q@_Am@yAw@_CqAyAiAaBeBuAqBc@y@i@wA}@_DaEkOuBwH{@eDoAaFkAwDo@aBsAgCaAyAe@m@_CoCqEoFqEkFyAiB}BwC]i@y@gB{@eCo@iD]SOCUCm@p@MRGKe@m@qBmCuBsCsAiBwA{BwCeFyDqGq@y@{@{@aAs@}A{AuCsCaDyC_C{Bi@k@^q@|AwBfAwBrBcE~@eBh@q@zCsBn@_@p@u@Xq@^{CTqCb@uDTwBLeAr@kBlA}Cr@_BvAmDVg@j@qAlAsBx@_ApBiB|@u@Zc@Zg@Xy@lAiF\\iBfB}IdA}DrBoG`AcCf@sARw@j@oBVa@VUl@c@`@MtBSfD_@xDe@fDa@dAInAQxBU^GRKXUR[H_@DUBSGc@K_A@i@Fm@\\c@TSb@WjBwAxCwBh@[b@KfAUv@Kd@Gn@CfBKfCOhAIxBU~KaDhA_@hAm@f@a@^c@`@o@~AwDYc@g@g@}CyCwAuAsBiBcCmB{@o@uA{@sGcDuDuBiDkBgEkC_Ao@cB_AoCiAkBi@oCa@GP@FI|@Qd@EPYfAIj@"));
//        array.add(new data("Tarnaka","02","TS01A9310","7:30","9:15","12","28","17.427235765506364", "78.53938920878029","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Bhongir","03","AP17A6314","6:50","9:30","16","22","17.512540816864856", "78.89074007185758","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Stadium","04","TS13A6734","6:55","8:50","18","57","17.405842178601972", "78.52035747133968","17.43658129370765", "78.71591244569122"));
//        array.add(new data("ECIL","05","TS06A5574","7:20","8:45","20","23","17.542839983169507", "78.66866481008998","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Yadagiri","06","AP12A6514","7:50","9:30","06","45","17.625152785104927", "78.94833566563422","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Secundrabad","07","AP02A6274","8:10","9:30","12","12","17.44130296880196", "78.48756486758869","17.43658129370765", "78.71591244569122"));
        array.add(new data("JNTU","08","TS14A3467","7:50","9:10","15","15","17.341067700085553", "78.56102888760826","17.43658129370765", "78.71591244569122",	"y|yhBo}n~MB]d@@UfD]lE_@`CgAhC}AzCyC~D}BnDkAvBaAxAUTiEpHuC`FOJm@~@gAhBkAvAQLGECWnBeCoAgAaEyCoHoFgCuBcHgFkAu@yBy@cCo@}EcAoPeD}Co@yDm@wDc@uAMw@?eCJoCZmG|@uCRoGH{Rg@}FQaEUQFEDO@mJYcFOsDMc@A@[B_@R@`ADbADN?ByBCoAM}ECsAHs@bAoF\\kA~BiG`D_IdBqEv@qBr@}BjAcDNaA@aAQiAYU{@SWYGo@H{BRy@vAeC~FiLzBqEhAmCj@gCdC_K^}AhAeGzC{Q|@{Ef@eDzAmLb@{CxAkDf@wAd@sBlA}FHqAQaFy@sJKaAAkAb@{B`@iA\\m@XK|@@pBN|AH^Gn@[r@s@d@m@Xq@rA}ENuBD}Ap@eFh@uC~@mCbB_Dh@u@|AwBh@y@l@e@xEcDrBoAxCy@vBgB~AoBvDiI}BgNmA}GMcAEwAHuEGm@WcAw@yCQw@OwAS_Bg@k@sBuAw@a@mA_@aAYe@W{AmBe@o@MSa@k@FUPs@Tu@f@gBbBkFnDgLh@uCHsAYaIQ_GBgB~A{L`A_HjDoR^wB?qAS{JAgBDkA@}@UcAaB}BeEaG}DuFQy@WaBqAoLWmAa@k@oCsBeCoAyBaAs@c@Yi@w@cDgAiF_AcFqAgFiGsUm@sBe@kA{EeKoGmNiAaCc@_BYkDc@kIGyCLsAZkAp@}BvAkFL}@@cD?oKEoGAaDKkAyA_Gq@qBu@y@gCgBcEwCoAkAe@uAi@aEg@iDc@w@yAsBe@s@S[wEc@m@KSGOIYF_I{@gWmCsVcCig@gFmE_@aLk@}EG_H@kJVgL~@qInA}Dn@YaCQmAiDj@}JbBqB\\yKnBkSlDcDl@Ei@Ce@EYa@g@q@u@cAaBc@i@eAq@_BaASy@Ue@i@k@qGmGu@i@kAWcASeAGw@KuAGgFA[Di@MsBONOb@c@jAgAnBmBxAwB`@iA~@wGl@{CdAqE?c@Ka@[iAa@}@e@sE_@}CMeCBo@n@qHHwBR}@l@c@Re@Jy@TaAC{ALiBQcBDiCGa@g@u@gCsDKSMy@UyAaAqCs@mCAwAIu@I_CUIo@g@i@wA}@oB_@u@SaAIUuAGeCh@[De@@}C{@aB_@gFoAyBYs@@{BEoAQ{@QiAm@cAy@g@SaAkA}CyCkE_E_E}CqGmD{F{C}G}DeDuB{C}AcEuAoCa@EXI|@Qd@_@xAIj@"));
//        array.add(new data("Madhapur","09","TS07A1654","7:40","9:05","07","32","17.447941527007067", "78.38735251209972","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Durgam Cheruvu","10","AP17A9845","6:55","9:25","18","38","17.429518229336974", "78.3889411825609","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Nagole","11","TS13A1265","7:55","9:15","15","34","17.375815358022884", "78.57132617109308","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Raidurg","12","TS01A7634","8:00","9:30","08","25","17.42395932365021", "78.38409035154389","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Stadium","13","AP19A6514","8:25","8:50","12","29","17.405842178601972", "78.52035747133968","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Uppal","01","TS19A6514","8:25","9:20","08","30","17.40266368465565","78.55544544478455","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Tarnaka","15","TS01A9310","6:50","9:30","20","39","17.427235765506364", "78.53938920878029","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Bhongir","16","AP17A6314","6:55","9:30","06","46","17.512540816864856", "78.89074007185758","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Stadium","17","TS13A6734","7:20","9:20","12","08","17.405842178601972", "78.52035747133968","17.43658129370765", "78.71591244569122"));
//        array.add(new data("ECIL","18","TS06A5574","7:50","9:05","15","03","17.542839983169507", "78.66866481008998","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Yadagiri","19","AP12A6514","8:10","9:25","07","26","17.625152785104927", "78.94833566563422","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Secundrabad","20","AP02A6274","8:25","9:30","18","48","17.44130296880196", "78.48756486758869","17.43658129370765", "78.71591244569122"));
//        array.add(new data("JNTU","21","TS14A3467","7:40","9:20","15","56","17.500114170100133", "78.3941790561241","17.43658129370765", "78.71591244569122"));
        array.add(new data("Raigiri","22","TS07A1654","6:55","9:15","12","39","17.51949591331333","78.92110529559483","17.43658129370765", "78.71591244569122",	"}w|iBygu`N{AcBJQ|@~@nAvAj@p@nBfCbAvA|A`CdCdE~BzE|ArDvBdG|@rCbBjHr@zDTvAd@tDZxC^|Ev@rNVlENbCZ|Bf@dCd@|AbAfCn@nAx@rAn@|@lArAzAvAbBlAjAp@rAn@bOdGpQhHlAj@rAr@pAz@jBbBfBlBbAtAz@|An@pAXr@l@jBt@fDVnBPzBDdDErCKtAYbCuBhNaCxNaC|OsH|e@q@hEkBzLc@nEOtBQhFEfHPvH`BzVfAxONbDFh@d@xDpAvIPvAXzDb@fHFfAP`BTvA\\pApAfD`DpHnAzCdFnLtCrGlLdXt@vAbAzAnAxAtDvDhCdCz@z@nA|AxCdEzBzD`@z@jBbEhCzFzDlIbIvQdC|F^bA|CpIhAjCn@pB|@dDVjAtBdIdA`Ex@tD~AdH^`Cp@nGz@xD`@dAd@dAf@z@|EbHlD`FnBtCdBrC~BtDrBjDx@vBXfAX`BL~@V|C`Bx]TzC`@zBh@|An@jA\\f@pBxBxAxAlAlAvB`C~@pA`BlC|BfEtA~B`BrCl@pAh@|AZpALd@XpBLtBFpB\\fLj@fRhA~]l@nPb@|Nn@zRj@xPLtCVrEXrHv@zXh@lQj@vJFx@f@xHh@nIR~Af@lBj@pAv@tA`AzAhE|GhGzJnAdCpAfDTr@`@R^nAT|@TfARfAvAbJ`BxLFn@NhAhBw@x@g@^G`@IpAAh@Hf@n@BJb@j@p@p@d@f@`@\\z@l@r@j@f@j@R\\l@z@~@dAp@~@r@pAt@lAt@xATr@Lt@LbANbBD~AN\\RPRFh@LdAd@nAx@p@v@v@nAdAtAh@h@f@\\j@`@j@`@RZd@?v@B`B@\\H`@HlBr@rAj@n@^h@Hh@Dr@Cb@Q\\g@fBWr@Cb@?NETSj@i@bAcAL]VIr@Qn@A|@IlAMz@Ij@QxBy@nBs@lA[l@c@Q`@Qt@SfBMnAEVYbAo@vBo@vC[fBYtBWpB|AlBrAbAvB`BfAdArA|ApAzArDzC|C~Dt@bAnAnAlA|@PLjChAn@XhGtCxBb@hCb@RBGP@ROdAO^Mp@MXIj@"));
//        array.add(new data("Durgam Cheruvu","23","AP17A9845","7:55","9:30","16","59","17.429518229336974", "78.3889411825609","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Nagole","24","TS13A1265","8:25","8:50","18","03","17.375815358022884", "78.57132617109308","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Raidurg","25","TS01A7634","8:20","9:20","07","09","17.41999527802678", "78.57631284602414","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Staduim","26","TS19A6514","6:50","9:30","06","18","17.405842178601972", "78.52035747133968","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Uppal","27","TS01A9310","6:55","9:30","12","25","17.40266368465565","78.55544544478455","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Tarnaka","29","AP17A6314","7:20","9:20","15","32","17.427235765506364", "78.53938920878029","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Stadium","28","TS13A6734","7:50","9:05","07","22","17.405842178601972", "78.52035747133968","17.43658129370765", "78.71591244569122"));
//        array.add(new data("ECIL","30","TS06A5574","8:10","9:25","18","53","17.542839983169507", "78.66866481008998","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Yadagairi","31","AP12A6514","8:25","9:30","15","50","17.625152785104927", "78.94833566563422","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Secundrabad","32","AP02A6274","7:40","9:30","08","46","17.625152785104927", "78.94833566563422","17.43658129370765", "78.71591244569122"));
//        array.add(new data("JNTU","33","TS14A3467","6:55","9:20","12","06","17.500114170100133", "78.3941790561241","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Madhapur","34","TS07A1654","7:55","9:15","16","39","17.447941527007067", "78.38735251209972","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Durgam Cheruvu","35","AP17A9845","8:25","9:30","18","17","17.429518229336974", "78.3889411825609","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Nagole","36","TS19A6514","7:30","8:50","20","15","17.375815358022884", "78.57132617109308","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Raidurg","37","TS01A9310","6:50","8:45","06","30","17.42395932365021", "78.38409035154389","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Stadium","38","AP17A6314","6:55","9:30","12","27","17.405842178601972", "78.52035747133968","17.43658129370765", "78.71591244569122"));
//        array.add(new data("Uppal","39","TS13A6734","7:20","9:20","15","59","17.40266368465565","78.55544544478455","17.43658129370765", "78.71591244569122"));

        BusAdapter_for_Search[] busAdapterForSearch = new BusAdapter_for_Search[3];
        busAdapterForSearch[0] =  new BusAdapter_for_Search(getContext(), array,true);
        setAdapter(busAdapterForSearch[0]);

        int hour = 1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime time = LocalTime.now();
            hour = time.getHour();
        }

//        if(hour<12) reverse(true);
//        else reverse(false);

        btnSwap.setOnClickListener(view -> reverse(!from.isFocusable()));

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i=0;
                arrNames.clear();
                if (!busNum.getText().toString().isEmpty()) {
                    for (; i < array.size(); i++)
                        if (array.get(i).BusNumber.equals(busNum.getText().toString())){
                            arrNames.add(array.get(i));
                            break;
                        }
                    busAdapterForSearch[1] = new BusAdapter_for_Search(getContext(), arrNames,true);
                    setAdapter(busAdapterForSearch[1]);
                }
                else {
                    String search;
                    if (from.isEnabled()) search = from.getText().toString().toLowerCase();
                    else search = to.getText().toString().toLowerCase();

                    if (!search.isEmpty()) {
                        for (i=0; i < array.size(); i++) if (array.get(i).Start.toLowerCase().equals(search)) arrNames.add(array.get(i));

                        busAdapterForSearch[2] = new BusAdapter_for_Search(getContext(), arrNames,true);
                        setAdapter(busAdapterForSearch[2]);
                    }
                    else Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
                }
                if (arrNames.isEmpty()) notFound.setAlpha(1f);
                else notFound.setAlpha(0f);
            }
        });




        return v;
    }

    void setAdapter(BusAdapter_for_Search busAdapterForSearch){
        recyclerView.setAdapter(busAdapterForSearch);
    }

    void reverse(boolean flag){
        from.setFocusable(flag);
        from.setFocusableInTouchMode(flag);
        from.setEnabled(flag);

        to.setFocusable(!flag);
        to.setFocusableInTouchMode(!flag);
        to.setEnabled(!flag);

        if(flag) {
            to.setText(from.getText().toString());
            from.setText("Ace Engineering College");
        }
        else{
            from.setText(to.getText().toString());
            to.setText("Ace Enginnering College");
        }
    }
}