arr = list(map(int, input().split()))


def binser(arrr, target):
    left = 0
    right = len(arrr) - 1
    
    while left <= right:
        mid = (left + right) // 2
        if arrr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
            
    return left

def lis(arr):
    if not arr:
        return 0
    
    tails = []
    
    for num in arr:
        if not tails or num > tails[-1]:
            tails.append(num)
        else:
            idx_to_replace = binser(tails, num)
            tails[idx_to_replace] = num
            
    return len(tails)


print(lis(arr))