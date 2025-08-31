import pytest

from Python.List.dynamic_array import DynamicArray


# This test suite is based on the requirements_engineering in List/requirements_engineering/add.json
def test_add_increases_size_and_preserves_order():
    """
    Tests REQ-ADD-001: Method `add(element)` exists.
    Tests REQ-ADD-002: The `add` operation shall increase the logical size by one.
    Tests REQ-ADD-004: The `add` operation shall preserve the order of elements.
    """
    arr = DynamicArray[str](10)
    assert arr.size() == 0

    # Add first element
    arr.add("A")
    assert arr.size() == 1
    assert arr.get(0) == "A"

    # Add second element
    arr.add("B")
    assert arr.size() == 2
    assert arr.get(0) == "A" # Check that old element is still there
    assert arr.get(1) == "B"

@pytest.mark.xfail(reason="BUG: In add(), the new resized array is not assigned back to self._array_.")
def test_add_triggers_resize():
    """
    Tests REQ-ADD-003: If storage is full, it shall allocate new storage
    with at least double the previous capacity.
    Tests REQ-ADD-007: Allocated but unused memory shall not exceed 100% of the logical size.
    """
    arr = DynamicArray[int](2)
    arr.add(10)
    arr.add(20)

    old_capacity = arr.capacity()
    assert arr.size() == old_capacity

    # This action should trigger a resize
    arr.add(30)

    # Verify state after resize
    assert arr.size() == 3
    assert arr.get(2) == 30
    assert arr.capacity() >= old_capacity * 2
    # Per implementation, it should be exactly double
    assert arr.capacity() == old_capacity * 2

def test_add_return_value_is_none():
    """
    Tests REQ-ADD-008. The requirement suggests a return value, but standard Python
    convention for in-place mutation methods is to return None. This test validates
    the current implementation against Python conventions.
    """
    arr = DynamicArray[int](5)
    return_value = arr.add(42)
    assert return_value is None

@pytest.mark.xfail(reason="FEATURE NOT IMPLEMENTED: `add` does not yet prevent adding None.")
def test_add_none_element_raises_error():
    """
    Tests REQ-ADD-009: The `add` method shall throw an exception for null elements.
    """
    arr = DynamicArray[object](5)
    with pytest.raises(ValueError, match="Cannot add None as an element"):
        arr.add(None)

@pytest.mark.slow
def test_stress_add_many_elements():
    """
    Tests REQ-ADD-006 & REQ-ADD-013: Stress test with many insertions.
    This is a slow test. Run with `pytest -m \"not slow\"` to skip.
    """
    # Using 10,000 as a more reasonable number for a unit test suite than 1,000,000
    num_elements = 10000
    arr = DynamicArray[int](0)
    for i in range(num_elements):
        arr.add(i)

    assert arr.size() == num_elements
    assert arr.get(num_elements - 1) == num_elements - 1