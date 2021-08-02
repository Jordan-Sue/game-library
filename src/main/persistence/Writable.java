package persistence;

import org.json.JSONObject;

// Interface based off of JsonSerializationDemo

public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
