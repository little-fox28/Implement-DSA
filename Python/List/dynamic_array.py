import ctypes
from operator import concat
from typing import Generic,TypeVar

T = TypeVar('T') #Generic type

def static_array(capacity: int):
    return (ctypes.py_object * capacity)()

def throw_index_exception(message: str = "Index out of bounds") -> str:
        raise ValueError(message)

class  DynamicArray(Generic[T]):
    def __init__(self, capacity: int = 10):
        if capacity <= 10:
            self._capacity_ = 10

        self._size_: int = 0 #number of element
        self._capacity_: int = capacity #volume of array
        self._array_ = static_array(capacity)

    def add(self, element: T) -> None:
        if self._size_ >= self._capacity_:
            if self._capacity_ == 0:
                self._capacity_ += 1
            else:
                self._capacity_ *= 2
                
            new_array = static_array(self._capacity_)
            for i in range(self.size()):
                new_array[i] = self._array_[i]
            self._array_ = new_array

        self._array_[self._size_] = element
        self._size_ += 1

    def remove_at(self, remove_index: int) -> T:
        if not 0 <= remove_index < self._size_:
            throw_index_exception()

        removed_element = self._array_[remove_index]
        for i in range(remove_index, self.size() -1):
            self._array_[i] = self._array_[i +1]

        self._array_[self.size() -1] = None
        self._size_ -= 1
        return removed_element

    def size(self) -> int:
        return self._size_

    def capacity(self) -> int:
        return self._capacity_
    
    def is_empty(self) -> bool:
        return self.size() == 0
    
    def get(self, index: int) -> T:
       if not 0 <= index < self.size():
           throw_index_exception()
       return self._array_[index]
    
    def set(self,index: int, element: T):
        self._array_[index] = element

    def clear(self):
        for i in range(self._size_):
            self._array_[i] = None
        self._size_ = 0
