package persistence;

import org.json.JSONObject;

// The following code was written by referring to the provided JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
