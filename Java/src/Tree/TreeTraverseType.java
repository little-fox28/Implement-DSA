package Tree;

public enum TreeTraverseType {
    PRE_ORDER,

    IN_ORDER,

    POST_ORDER,

    LEVEL_ORDER;

    public String getDescription() {
        switch (this) {
            case PRE_ORDER:
                return "Pre-order traversal: root -> left -> right";
            case IN_ORDER:
                return "In-order traversal: left -> root -> right";
            case POST_ORDER:
                return "Post-order traversal: left -> right -> root";
            case LEVEL_ORDER:
                return "Level-order traversal: level by level";
            default:
                throw new AssertionError("Unknown traversal type: " + this);
        }
    }

    public static TreeTraverseType fromString(String value) {
        switch (value.toUpperCase()) {
            case "PRE_ORDER":
                return PRE_ORDER;
            case "IN_ORDER":
                return IN_ORDER;
            case "POST_ORDER":
                return POST_ORDER;
            case "LEVEL_ORDER":
                return LEVEL_ORDER;
            default:
                throw new IllegalArgumentException("Unknown traversal type: " + value);
        }
    }
}
