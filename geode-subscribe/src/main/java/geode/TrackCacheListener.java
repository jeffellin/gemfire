package gemfire;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.CacheListener;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.RegionEvent;
import org.apache.geode.pdx.PdxInstance;

@Slf4j
public class TrackCacheListener implements CacheListener<Long, PdxInstance> {


    public void afterCreate(EntryEvent<Long, PdxInstance> entryEvent) {
        printTrackInfo(entryEvent, "afterCreate");
    }

    public void afterUpdate(EntryEvent<Long, PdxInstance> entryEvent) {
        printTrackInfo(entryEvent, "afterUpdate");


    }

    private void printTrackInfo(EntryEvent<Long, PdxInstance> entryEvent, String afterUpdate) {

        String name = (String) entryEvent.getSerializedNewValue().getDeserializedValue().getField("name");
        Long pos = (Long) entryEvent.getSerializedNewValue().getDeserializedValue().getField("position");
        System.out.println("Track for "+name+" updated position " + pos);

    }

    public void afterInvalidate(EntryEvent<Long, PdxInstance> entryEvent) {

    }

    public void afterDestroy(EntryEvent<Long, PdxInstance> entryEvent) {

    }

    public void afterRegionInvalidate(RegionEvent<Long, PdxInstance> regionEvent) {

    }

    public void afterRegionDestroy(RegionEvent<Long, PdxInstance> regionEvent) {

    }

    public void afterRegionClear(RegionEvent<Long, PdxInstance> regionEvent) {

    }

    public void afterRegionCreate(RegionEvent<Long, PdxInstance> regionEvent) {

    }

    public void afterRegionLive(RegionEvent<Long, PdxInstance> regionEvent) {

    }
}

