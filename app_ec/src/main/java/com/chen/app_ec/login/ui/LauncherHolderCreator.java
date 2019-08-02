package com.chen.app_ec.login.ui;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class LauncherHolderCreator implements CBViewHolderCreator<TestLunchHolder> {
    @Override
    public TestLunchHolder createHolder() {
        return new TestLunchHolder();
    }
}
