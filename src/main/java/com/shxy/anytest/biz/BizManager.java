package com.shxy.anytest.biz;

import java.util.EnumSet;

public class BizManager {
    private EnumSet<BizBit> mCurrentBiz;

    public BizManager() {
        mCurrentBiz = EnumSet.noneOf(BizBit.class);
    }

    public void enableBiz(Biz biz) {
        if (!isCompatible(biz)) {
            throw new IllegalArgumentException(String.format("current bizs: %1$s, try to enable biz: %2$s -> %3$s",mCurrentBiz.toString(),biz.toString(),biz.mBizBitEnumSet.toString()));
        }
        mCurrentBiz.add(biz.mBizBit);
    }

    public boolean isCompatible(Biz biz) {
        EnumSet<BizBit> resSet = mCurrentBiz.clone();
        resSet.retainAll(biz.mBizBitEnumSet);
        return resSet.isEmpty();
    }

    public void disableBiz(Biz biz) {
        mCurrentBiz.remove(biz.mBizBit);
    }

    public static void main(String[] args) {
        BizManager bizManager = new BizManager();
        bizManager.enableBiz(Biz.ROBOT);
        System.out.println(bizManager.isCompatible(Biz.COMMENT));
        System.out.println(bizManager.isCompatible(Biz.PK));
        bizManager.enableBiz(Biz.COMMENT);
        bizManager.disableBiz(Biz.ROBOT);
        bizManager.enableBiz(Biz.PK);
        bizManager.enableBiz(Biz.PET);
    }

    private enum Biz {
        PK(EnumSet.of(BizBit.ROBOT, BizBit.PET), BizBit.PK),
        COMMENT(EnumSet.noneOf(BizBit.class), BizBit.COMMENT),
        ROBOT(EnumSet.of(BizBit.PK, BizBit.PET), BizBit.ROBOT),
        PET(EnumSet.of(BizBit.ROBOT, BizBit.PK), BizBit.PET),;

        Biz(EnumSet<BizBit> bizBitEnumSet, BizBit bizBit) {
            mBizBitEnumSet = bizBitEnumSet;
            mBizBit = bizBit;
        }

        private EnumSet<BizBit> mBizBitEnumSet;
        private BizBit mBizBit;
    }

    private enum BizBit {
        PK,
        COMMENT,
        ROBOT,
        PET,;
    }
}
