package com.ashomok.ocrme.get_more_requests;

/**
 * Created by iuliia on 3/2/18.
 */

import android.app.Activity;

import com.ashomok.ocrme.billing.BillingProvider;
import com.ashomok.ocrme.billing.BillingProviderImpl;
import com.ashomok.ocrme.billing.model.SkuRowData;
import com.ashomok.ocrme.get_more_requests.row.free_options.PromoListFreeOptions;
import com.ashomok.ocrme.get_more_requests.row.free_options.PromoRowFreeOptionData;
import com.ashomok.ocrme.get_more_requests.row.free_options.UiManagingDelegate;
import com.ashomok.ocrme.get_more_requests.row.free_options.option_delegates.FollowUsOnFbDelegate;
import com.ashomok.ocrme.get_more_requests.row.free_options.option_delegates.LoginToSystemDelegate;
import com.ashomok.ocrme.get_more_requests.row.free_options.option_delegates.RateAppDelegate;
import com.ashomok.ocrme.get_more_requests.row.free_options.option_delegates.WatchVideoDelegate;
import com.ashomok.ocrme.get_more_requests.row.paid_options.option_delegates.Batch100Delegate;
import com.ashomok.ocrme.get_more_requests.row.paid_options.option_delegates.Batch5Delegate;
import com.ashomok.ocrme.get_more_requests.row.paid_options.option_delegates.SubscriptionMonthlyDelegate;
import com.ashomok.ocrme.get_more_requests.row.paid_options.option_delegates.SubscriptionYearlyDelegate;
import com.ashomok.ocrme.update_to_premium.UpdateToPremiumPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link UpdateToPremiumPresenter}.
 */
@Module
public abstract class GetMoreRequestsModule {
    @Binds
    abstract GetMoreRequestsContract.Presenter getMoreRequestsPresenter(
            GetMoreRequestsPresenter presenter);

    @Provides
    static List<PromoRowFreeOptionData> providePromoList(){
        return PromoListFreeOptions.getList();
    }

    @Provides
    static BillingProvider provideBillingProvider(BillingProviderImpl billingProvider){
        return billingProvider;
    }

    @Provides
    static Activity provideActivity(GetMoreRequestsActivity activity) {
        return activity;
    }

    @Provides
    static Map<String, UiManagingDelegate> provideUiDelegatesForFree(
            LoginToSystemDelegate loginToSystemDelegate,
            WatchVideoDelegate watchVideoDelegate,
            RateAppDelegate rateAppDelegate,
            FollowUsOnFbDelegate followUsOnFbDelegate){
        Map<String, UiManagingDelegate> uiDelegates = new HashMap<>();
        uiDelegates.put(WatchVideoDelegate.ID, watchVideoDelegate);
        uiDelegates.put(LoginToSystemDelegate.ID, loginToSystemDelegate);
        uiDelegates.put(RateAppDelegate.ID, rateAppDelegate);
        uiDelegates.put(FollowUsOnFbDelegate.ID, followUsOnFbDelegate);
        return uiDelegates;
    }

    @Provides
    static Map<String, com.ashomok.ocrme.get_more_requests.row.paid_options.UiManagingDelegate>
    provideUiDelegatesForPaid(Batch5Delegate batch5Delegate,
                              Batch100Delegate batch100Delegate,
                              SubscriptionMonthlyDelegate subscriptionMonthlyDelegate,
                              SubscriptionYearlyDelegate subscriptionYearlyDelegate){

        Map<String, com.ashomok.ocrme.get_more_requests.row.paid_options.UiManagingDelegate> uiDelegates = new HashMap<>();
        uiDelegates.put(BillingProviderImpl.SCAN_IMAGE_REQUESTS_5_SKU_ID, batch5Delegate);
        uiDelegates.put(BillingProviderImpl.SCAN_IMAGE_REQUESTS_100_SKU_ID, batch100Delegate);
        uiDelegates.put(BillingProviderImpl.PREMIUM_MONTHLY_SKU_ID, subscriptionMonthlyDelegate);
        uiDelegates.put(BillingProviderImpl.PREMIUM_YEARLY_SKU_ID, subscriptionYearlyDelegate);
        return uiDelegates;

    }

}
