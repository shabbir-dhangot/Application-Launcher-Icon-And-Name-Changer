package com.sid.appiconlauncher;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnChangeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnChangeName = (Button) findViewById(R.id.btnChangeName);

        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, btnChangeName);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_change_app_name, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "App Name and icon will change to " + item.getTitle() + "in 10 Seconds", Toast.LENGTH_SHORT).show();
                        if (item.getTitle().equals(getString(R.string.app_red))) {
                            getPackageManager().setComponentEnabledSetting(
                                    new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Red"),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                            try {
                                getPackageManager().setComponentEnabledSetting(
                                        new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Green"),
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                                getPackageManager().setComponentEnabledSetting(
                                        new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Blue"),
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (item.getTitle().equals(getString(R.string.app_green))) {
                            getPackageManager().setComponentEnabledSetting(
                                    new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Green"),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                            try {
                                getPackageManager().setComponentEnabledSetting(
                                        new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Red"),
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                                getPackageManager().setComponentEnabledSetting(
                                        new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Blue"),
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            getPackageManager().setComponentEnabledSetting(
                                    new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Blue"),
                                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                            try {
                                getPackageManager().setComponentEnabledSetting(
                                        new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Red"),
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                                getPackageManager().setComponentEnabledSetting(
                                        new ComponentName("com.sid.appiconlauncher", "com.sid.appiconlauncher.MainActivity-Green"),
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select App name");
        menu.add(0, v.getId(), 0, getString(R.string.app_red));//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, getString(R.string.app_green));
        menu.add(0, v.getId(), 0, getString(R.string.app_blue));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(getString(R.string.app_red))) {
            Toast.makeText(getApplicationContext(), "Red", Toast.LENGTH_LONG).show();
        } else if (item.getTitle().equals(getString(R.string.app_green))) {
            Toast.makeText(getApplicationContext(), "Green", Toast.LENGTH_LONG).show();
        } else if (item.getTitle().equals(getString(R.string.app_blue))) {
            Toast.makeText(getApplicationContext(), "Blue", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
