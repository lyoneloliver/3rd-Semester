import heapq

#goal = ('_', '1', '2', '3', '4', '5', '6', '7', '8') 

puzzle = tuple(input("Enter the puzzle, use _ for empty space (space separated): ").split())
goal = tuple(input("Enter the goal, use _ for empty space (space separated): ").split())

paths = {}

def a_star():
    paths[puzzle] = None
    open = []
    close = set()
    heapq.heappush(open, (h(puzzle), puzzle, 0, None))

    while open:
        f_value, state_value, g_value, parent_value = heapq.heappop(open)

        if state_value == goal:
            steps = []
            s = state_value
            while s is not None:
                steps.append(s)
                s = paths[s]
            steps.reverse()

        
            for i, st in enumerate(steps):
                print(str(i) + ".")
                for j in range(0, 9, 3):
                    print(st[j:j+3])
                print()
            print("Goal found")
            print("Steps to reach goal:", len(steps)-1)
            return

        if state_value in close:
            continue

        close.add(state_value)

        successors = A_move(state_value, g_value)
        for state_s, g_s in successors:
            if state_s not in close:
                paths[state_s] = state_value
                heapq.heappush(open, (g_s + h(state_s), state_s, g_s, state_value))
    
    print("No solution found with A*")


def blank(state):
    for i in range(9):
        if state[i] == '_':
            return i

def h(state):
    x = 0
    for i in range(9):
        if state[i] != goal[i] and state[i] != '_':
            x += 1
    return x

def A_move(state, g):
    successors = []
    b = blank(state)

    # up
    if b > 2:
        state2 = list(state)
        state2[b], state2[b-3] = state2[b-3], state2[b]
        successors.append((tuple(state2), g+1))

    # down
    if b < 6:
        state2 = list(state)
        state2[b], state2[b+3] = state2[b+3], state2[b]
        successors.append((tuple(state2), g+1))

    # right
    if b not in [2,5,8]:
        state2 = list(state)
        state2[b], state2[b+1] = state2[b+1], state2[b]
        successors.append((tuple(state2), g+1))

    # left
    if b not in [0,3,6]:
        state2 = list(state)
        state2[b], state2[b-1] = state2[b-1], state2[b]
        successors.append((tuple(state2), g+1))

    return successors


def greedy_bfs(puzzle):
    visited=set()
    parents={}
    parent=puzzle
    parents[puzzle] = None
    
    while(1):
        visited.add(tuple(puzzle))
        parent=puzzle
        
        puzzle=bfs_move(puzzle,visited)
        
        if puzzle is None:
            print("No solution found with Greedy BFS")
            return
        
        parents[tuple(puzzle)] = (parent)
        
        if(h(puzzle)==0):
            steps = []
            s = puzzle
            while s is not None:
                steps.append(s)
                s = parents[s]
            steps.reverse()
            
            for i, st in enumerate(steps):
                print(str(i) + ".")
                for j in range(0, 9, 3):
                    print(st[j:j+3])
                print()
            print("Goal found")
            print("Steps to reach goal:", len(steps)-1)
            return
    


def bfs_move(state,visited):
   
    b = blank(state)
    least=None
    x=100
    
    # up
    if b > 2:
        state2 = list(state)
        state2[b], state2[b-3] = state2[b-3], state2[b]
        if(tuple(state2) not in visited)and (h(state2)<x  or not least):
            x=h(state2)
            least=state2
        
    # down
    if b < 6:
        state2 = list(state)
        state2[b], state2[b+3] = state2[b+3], state2[b]
        if(tuple(state2) not in visited)and (h(state2)<x  or not least):
            x=h(state2)
            least=state2
            
    # right
    if b not in [2,5,8]:
        state2 = list(state)
        state2[b], state2[b+1] = state2[b+1], state2[b]
        if(tuple(state2) not in visited)and (h(state2)<x  or not least):
            x=h(state2)
            least=state2

    # left
    if b not in [0,3,6]:
        state2 = list(state)
        state2[b], state2[b-1] = state2[b-1], state2[b]
        if(tuple(state2) not in visited)and (h(state2)<x  or not least):
            x=h(state2)
            least=state2
            
            
    if least is None:
        return None
    else:
        return tuple(least)
       

    
x=input("Enter 1 for A* or 2 for Greedy BFS: ")
if(x=='1'):
    a_star()
elif(x=='2'):
    greedy_bfs(puzzle)