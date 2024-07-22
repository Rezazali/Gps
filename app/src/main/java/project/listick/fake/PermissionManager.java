package project.listick.fake;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;

import java.util.List;

/*
 * Created by LittleAngry on 27.12.18 (macOS 10.12)
 * */
public class PermissionManager {


    public static boolean isMockLocationsEnabled(Context context) {
        boolean isMockLocationEnabled;
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

        if (appOps == null)
            return false;

        int mockLocationResult = appOps.checkOpNoThrow(AppOpsManager.OPSTR_MOCK_LOCATION, Process.myUid(), BuildConfig.APPLICATION_ID);

        isMockLocationEnabled = mockLocationResult == AppOpsManager.MODE_ALLOWED;
        return true;
    }

    public static boolean isSystemApp(Context context) {
        boolean isSystemApp = false;
        try {
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> list = pm.getInstalledPackages(0);


            for (PackageInfo pi : list) {
                ApplicationInfo ai = pm.getApplicationInfo(pi.packageName, 0);

                if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    if (pi.packageName.equals(context.getPackageName()))
                        isSystemApp = true;
                }
            }
            return isSystemApp;
        } catch (Exception e) {
            return isSystemApp;
        }
    }
}
