package seedu.waddle.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;

/**
 * Jackson-friendly version of {@link Item}.
 */
public class JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String description;
    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("description") String description,
                           @JsonProperty("priority") String priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        description = source.getDescription();
        priority = source.getPriority().priority;
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Item.class.getSimpleName()));
        }
        /*
        TODO: check if description is valid
        if (!Item.isValidDescription(description)) {
            throw new IllegalValueException(Item.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);
        */
        final String modelDescription = description;

        if (priority == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }

        final Priority modelPriority = new Priority(priority);

        return new Item(modelDescription, modelPriority);
    }

}
