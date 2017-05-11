package module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rahulkumar on 03/11/16.
 */

@Module
public class ActivityLevelModule {
    private final Activity activity;

    public ActivityLevelModule(Activity activity){
        this.activity=activity;
    }

    @Provides
    Activity getActivity(){
        return  activity;
    }

}
