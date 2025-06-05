import ctypes

array = (ctypes.py_object * 5)()
new_array = (ctypes.py_object * 10)()

array[0] = 16
array[1] = 28
array[2] = 32
array[3] = 49
array[4] = 53

for i in range(len(array)):
    print(i)

# index = 0
# remove_index = 2
# for i in range(len(array)):
#     if i == remove_index:
#         continue
#     new_array[index] = array[i]
#     index += 1

# for element in new_array:
#     print(element)