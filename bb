Understanding the Git Workflow
If you don¡¯t understand the motivation behind Git¡¯s design, you¡¯re in for a world of hurt. With enough flags you can force Git to act the way you think it should instead of the way it wants to. But that¡¯s like using a screwdriver like a hammer; it gets the job done, but it¡¯s done poorly, takes longer, and damages the screwdriver.

Consider how a common Git workflow falls apart.

Create a branch off Master
Work
Merge it back to Master when done
Most of the time this behaves as you expect because Master changed since you branched. Then one day you merge a feature branch into Master, but Master hasn¡¯t diverged. Instead of creating a merge commit, Git points Master to the latest commit on the feature branch, or ¡°fast forwards.¡± (Diagram)

Unfortunately, your feature branch contained checkpoint commits, frequent commits that back up your work but captures the code in an unstable state. Now these commits are indistinguishable from Master¡¯s stable commits. You could easily roll back into a disaster.

So you add a new rule: ¡°When you merge in your feature branch, use ¨Cno-ff to force a new commit.¡± This gets the job done, and you move on.

Then one day you discover a critical bug in production, and you need to track down when it was introduced. You run bisect but keep landing on checkpoint commits. You give up and investigate by hand.

You narrow the bug to a single file. You run blame to see how it changed in the last 48 hours. You know it¡¯s impossible, but blame reports the file hasn¡¯t been touched in weeks. It turns out blame reports changes for the time of the initial commit, not when merged. Your first checkpoint commit modified this file weeks ago, but the change was merged in today.

The no-ff band-aid, broken bisect, and blame mysteries are all symptoms that you¡¯re using a screwdriver as a hammer.



From:
     https://sandofsky.com/blog/git-workflow.html