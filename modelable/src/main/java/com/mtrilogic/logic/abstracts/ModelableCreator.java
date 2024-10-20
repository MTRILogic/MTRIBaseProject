package com.mtrilogic.logic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public abstract class ModelableCreator<M extends Modelable> implements Parcelable.ClassLoaderCreator<M> {

    /*==============================================================================================
    PROTECTED ABSTRACT METHODS
    ==============================================================================================*/

    protected abstract M createFromData(Bundle data);

    /*==============================================================================================
    OVERRIDE PUBLIC FINAL METHODS
    ==============================================================================================*/

    @Override
    public final M createFromParcel(Parcel source, ClassLoader loader) {
        Bundle data;
        if (source != null && loader != null) {
            data = source.readBundle(loader);
        } else {
            data = null;
        }
        return createFromData(data);
    }

    @Override
    public final M createFromParcel(Parcel source) {
        return createFromData(null);
    }
}
