package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class ModelableCreator<M extends Modelable> implements Parcelable.ClassLoaderCreator<M> {

    protected abstract M createFromData(Bundle data);

    @Override
    public M createFromParcel(Parcel source, ClassLoader loader) {
        Bundle data;
        if (source != null && loader != null) {
            data = source.readBundle(loader);
        } else {
            data = null;
        }
        return createFromData(data);
    }

    @Override
    public M createFromParcel(Parcel source) {
        return createFromData(null);
    }
}
