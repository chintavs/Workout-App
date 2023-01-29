# Social Workout


## Design Document

William Koonce

Sean Arthur 

Colton Dalton 

Vishvak Chintalapalli 

Pratik Chaudhari 

## Introduction 

Do you struggle to keep track of your workouts? Do you wish you could compare your growth with that of your friends? Social Workout can assist you by: 
- Reminding you to record your workout for the day. 
- Tracking your fitness growth.
- Comparing your growth with that of your friends.
- Creating workout plans based on your previous records. 
Using your Android device, Social Workout is a great way to keep yourself and your friends both motivated and accountable in the gym. Set reminders to remember to workout and record your workouts. Watch how you’ve grown over a given time period and compare that with the growth of your friends. 

## Storyboard 
[Social Workout Storyboard](https://app.milanote.com/1Pmdwa1OSwWD85/social-workout-storyboard?p=URvyliO0qLa)

## Functional Requirements 
### Requirement 1: Search for Exercises  
#### Scenario 
As a user interested in working out, I want to be able to search up different kinds of exercises and sort by type so I can create a routine and track my progress. IE Strength, Cardio, Endurance, Mobility, GYM Machine  

#### Dependencies 
Database of exercises is available and accessible. 

#### Assumptions 
Database of exercises is available and accessible. 

#### Examples 
1.1 

**Given** a feed of exercises is available.
**When** I search for “Bicep Curls”
**Then** I should receive a couple results that work out the Biceps. 

1.2 

**Given** a feed of exercises is available.
**When** I search for “Bench Press”
**Then** I should receive a couple results related to “Bench Press” exercises 

### Requirement 2: Record workout. 
#### Scenario 
As a user who would like to work out, I want to be able to enter and save details of my workouts: workout type, weight lifted, time completed, reps completed per set, sets completed, duration of workout. 

#### Dependencies 
Database of exercises is available. 
Function to calculate calories burned is active. 

#### Assumptions 
Weight lifted is record in lbs.
User noted their reps completed.
User noted their sets completed.
User noted weight lifted during workout.
User noted what time the workout was completed. 
User is logged into the application 

#### Examples 
1.1 

**Given** that the user has completed a workout 
**When** 
- Select “Record workout”
- Select the “bench-press” workout
- Add the details:
  - Workout Type: Chest
  - Weight Lifted: 60lbs
  - Time Completed: 3:45pm
  - Reps Completed per Set: 10
  - Sets Completed: 3
  - Duration of Workout: 8 minutes
- Click “Post” 
**Then** when I navigate to my profile page, I should see at least one posted workout with the details:
 - Workout Type: Chest
 - Weight Lifted: 60lbs
 - Time Completed: 3:45pm
 - Reps Completed per Set: 10
 - Sets Completed: 3
 - Duration of Workout: 8 minutes

1.2 

**Given** that the user has completed a workout 
**When** 
- Select “Record workout” 
- Select the “squat” workout 
- Add the details: 
  - Workout Type: Legs 
  - Weight Lifted: 120lbs 
  - Time Completed: 4:00pm 
  - Reps Completed per Set: 10 
  - Sets Completed: 3 
  - Duration of Workout: 10 minutes 
- Click “Post” 
**Then** when I navigate to my profile page, I should see at least one posted workout with the details: 
- Workout Type: Legs 
- Weight Lifted: 120lbs 
- Time Completed: 4:00pm 
- Reps Completed per Set: 10 
- Sets Completed: 3 
- Duration of Workout: 10 minutes  

### Requirement 3: Track Progress. 

#### Scenario 
As a User, I want to be able to track my workout progress and measure goals including type of workouts completed, sets completed, duration of workouts completed, total calories burned, and how much I have left to reach my goals. 

#### Examples
1.1

**Given** that the user has set their workout goals and completed a workout 
**When** the user opens the app 
**Then** the user can view their progress towards their goals, including the number of workouts completed, total duration of workouts completed, total number of calories burned, and how much they have left to reach their goal.  

## Class Diagram 
![ClassDiagram](https://github.com/chintavs/Workout-App/blob/main/WorkoutAppDiagram.drawio.png)

## Class Diagram Description 
MainActivity: The first screen the user sees. This will have the users profile along with the different workouts they’ve been recommended or created 

RetrofitInstance: Boostrap class required for Retrofit. 

User: Noun class that represents the user profile 

Workout: Noun class that represents the recommended workouts and created workouts 

IUserDAO: Interface for Retrofit to find and parse User JSON. 

IWorkoutDAO: Interface for Room to persist Workout data. 

## Scrum Roles 
Scrum Master - Sean Arthur 

UI Specialist I - William Koonce 

UI Specialist II - Vishvak Chintalapalli 

Integration Specialist I - Colton Dalton 

Integration Specialist II - Pratik Chaudhari 

## Weekly Meeting 
Thursday at 5:30 PM on Teams 

## GitHub 
https://github.com/chintavs/Workout-App 
